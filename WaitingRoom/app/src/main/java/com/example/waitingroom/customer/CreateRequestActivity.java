package com.example.waitingroom.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Business;
import com.example.waitingroom.types.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CallRequestActivity shows the businesses details and prompts
 * for input of name, phone, and reason for call before requesting a
 * call from the business.
 */
public class CreateRequestActivity extends AppCompatActivity {
    Business business;
    //Validation code sent by the SYSTEM
    String validationCodeSent;
    //Request details from the user
    Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_create_request);

        business = (Business) getIntent().getSerializableExtra("business");
        TextView businessNameTextView = findViewById(R.id.businessNameTextView);
        businessNameTextView.setText(business.getName());

        TextView requestCall = findViewById(R.id.headingTextView);
        System.out.println(business.getLocation());
        requestCall.setText(business.getLocation());

        Button button = findViewById(R.id.submitVerificationCodeButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView verifyText = findViewById(R.id.verificationCodeTextField);
                verifyCode(verifyText.getText().toString());
            }
        });

    }

    public void createCall(View view) {
        TextView requestNameField = findViewById(R.id.requestName);
        TextView requestPhoneField =findViewById(R.id.requestPhoneNumber);
        TextView requestDescriptionField = findViewById(R.id.requestDescription);
        String requestName = requestNameField.getText().toString();
        String requestPhoneNumber = requestPhoneField.getText().toString();
        String requestDescription = requestDescriptionField.getText().toString();
        boolean valid = true;
        if (requestNameField.getText().toString().trim().equalsIgnoreCase("")) {
            requestNameField.setError(getString(R.string.invalidField));
            valid = false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(requestPhoneNumber);
        if (!matcher.matches()) {
            requestPhoneField.setError(getString(R.string.invalidNumber));
            valid = false;
        }
        if (requestDescriptionField.getText().toString().trim().equalsIgnoreCase("")) {
            requestDescriptionField.setError(getString(R.string.invalidField));
            valid = false;
        }
        if (valid) {
            //Create a request and store it in the db
            request = new Request(requestName, requestPhoneNumber, requestDescription, new Timestamp(System.currentTimeMillis()).toString());

            //Validate the phone number via FirebaseAuth
            validateWithPhoneAuth(requestPhoneNumber);
        }

    }

    private void validateWithPhoneAuth(String phoneNum) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuthSettings firebaseAuthSettings = mAuth.getFirebaseAuthSettings();
        //firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber("+16045554562"	, "654321");

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+16045554562")       // Phone number to verify
//                        .setPhoneNumber("+1" + phoneNum)
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    //Callbacks for phone auth
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code = credential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            System.out.println(e.getMessage());
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                System.out.println(e.getMessage());
            } else if (e instanceof FirebaseTooManyRequestsException) {
                System.out.println(e.getMessage());
            }

        }
        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.

            //Log.d(TAG, "onCodeSent:" + verificationId);
            System.out.println("sent");
            validationCodeSent = verificationId;

            TextView verificationField = findViewById(R.id.verificationCodeTextField);
            Button verifyButton = findViewById(R.id.submitVerificationCodeButton);

            verificationField.setVisibility(View.VISIBLE);
            verifyButton.setVisibility(View.VISIBLE);

        }
    };

    //If the code is code is correct create the request
    private void verifyCode(String validationCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(validationCodeSent, validationCode);
        signIntheUser(credential);
    }

    //Goes to the PositionInLine activity
    private void showPositionInLineActivity() {
        Intent intent = new Intent(CreateRequestActivity.this, PositionInLineActivity.class);
        intent.putExtra("request", request);
        intent.putExtra("business", business);
        startActivity(intent);
    }

    //Stores the request in the DB
    private void createRequest(Request request) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Businesses").child(business.getName().toLowerCase()).child("requests");
        DatabaseReference requestReference = myRef.push();
        requestReference.setValue(request);
    }

    private void signIntheUser(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithCredential(credential).addOnCompleteListener(CreateRequestActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    createRequest(request);
                    showPositionInLineActivity();
                } else {
                    System.out.println("Auth unsuccessful");
                }
            }
        });
    }
}

