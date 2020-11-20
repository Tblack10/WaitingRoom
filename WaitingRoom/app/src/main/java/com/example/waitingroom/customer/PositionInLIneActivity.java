package com.example.waitingroom.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.waitingroom.R;

/**
 * PositionInLIneActivity displays your current position in the caller queue
 */
public class PositionInLIneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_position);
    }
}