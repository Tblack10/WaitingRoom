package com.example.waitingroom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class RequestAdapter extends ArrayAdapter<Request> {
    final Context _context;

    public RequestAdapter(Context context, ArrayList<Request> Requests) {
        super(context, 0, Requests);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Request request = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_request_layout, parent, false);
        }

        // Lookup view for data population
        TextView name = convertView.findViewById(R.id.name);
        TextView title = convertView.findViewById(R.id.title);
        TextView description = convertView.findViewById(R.id.description);

        name.setText(request.getCustomer().getName());
        title.setText(request.getTitle());
        description.setText(request.getDescription());


        // Return the completed view to render on screen
        return convertView;
    }
}
