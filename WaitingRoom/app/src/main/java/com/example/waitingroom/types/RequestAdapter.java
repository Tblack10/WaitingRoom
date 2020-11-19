package com.example.waitingroom.types;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Request;

import java.util.ArrayList;
public class RequestAdapter extends ArrayAdapter<Request> {
    final Context _context;

    public RequestAdapter(Context context, ArrayList<Request> Requests) {
        super(context, 0, Requests);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Request request = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_request_layout, parent, false);
        }
        TextView name = convertView.findViewById(R.id.name);
        TextView description = convertView.findViewById(R.id.description);
        TextView date = convertView.findViewById(R.id.publishedAt);
        TextView phoneNumber = convertView.findViewById(R.id.phoneNumber);
        name.setText(request.getName());
        description.setText(request.getDescription());
        date.setText(request.getDate());
        phoneNumber.setText(request.getPhoneNumber().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));
        return convertView;
    }
}
