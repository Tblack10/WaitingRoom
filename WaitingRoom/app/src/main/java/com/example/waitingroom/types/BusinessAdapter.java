package com.example.waitingroom.types;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.waitingroom.R;
import com.example.waitingroom.types.Request;

import java.util.ArrayList;

/**
 * Adapter for the Business model
 */
public class BusinessAdapter extends ArrayAdapter<Request> {
    final Context _context;

    /**
     * Adapter for the business object
     * @param context
     * @param Requests
     */
    public BusinessAdapter(Context context, ArrayList<Request> Requests) {
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

        name.setText(request.getName());
        description.setText(request.getDescription());


        // Return the completed view to render on screen
        return convertView;
    }
}
