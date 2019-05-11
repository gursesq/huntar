package com.example.prototype.CreatAR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prototype.Event.ARPoint;
import com.example.prototype.R;

import java.util.ArrayList;

public class CreatorAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<ARPoint> values;
    private  String[] vals;
    private final ListView myListView;

    public CreatorAdapter(Context context, ArrayList<ARPoint> values, String[] vals, ListView listView) {
        super(context, -1, vals);
        this.context = context;
        this.values = values;
        this.vals = vals;
        myListView = listView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_creator, parent, false);

        TextView txtName = rowView.findViewById(R.id.name);
        Button btnTrash = rowView.findViewById(R.id.trash);

        txtName.setText(values.get(position).getName());

        btnTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventCreator.remove(position);
            }
        });

        return rowView;
    }
}
