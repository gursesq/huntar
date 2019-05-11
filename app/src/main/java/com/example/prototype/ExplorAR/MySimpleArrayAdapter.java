package com.example.prototype.ExplorAR;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prototype.HuntAR.MapsActivity;
import com.example.prototype.Event.MyEvent;
import com.example.prototype.Event.OpenEvent;
import com.example.prototype.R;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private ArrayList<MyEvent> values;
        private String[] vals;
        private final ListView myListView;

        public MySimpleArrayAdapter(Context context, ArrayList<MyEvent> values, String[] vals, ListView listView) {
            super(context, -1, vals);
            this.context = context;
            this.values = values;
            myListView = listView;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if ( values.get(position) instanceof OpenEvent){
                final View rowView = inflater.inflate(R.layout.adapter_open, parent, false);
                TextView txtDescription = rowView.findViewById(R.id.txtDescription);
                txtDescription.setText(values.get(position).getDescription());
                final Button btnFollow = rowView.findViewById(R.id.btnFollow);
                Button btnPlay = rowView.findViewById(R.id.btnPlay);

                btnFollow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        values.get(position-1).toggleFollowed();
                        values.get(position).setFollowed(values.get(position-1).getFollowed());
                        if ( values.get(position-1).getFollowed() ) {
                            myListView.getChildAt(position - 1).setBackgroundColor(Color.rgb(51, 153, 255));
                        }
                        else {
                            myListView.getChildAt(position - 1).setBackgroundColor(Color.WHITE);
                        }
                    }
                });

                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( getContext(), MapsActivity.class);
                        getContext().startActivity(intent);
                    }
                });
                return rowView;
            }
            else {

                View rowView = inflater.inflate(R.layout.adapter_closed, parent, false);


                Button btnDropdownArrow = rowView.findViewById(R.id.dropdown_arrow);
                if ( values.get(position).getOpen() ) {
                    btnDropdownArrow.setBackgroundResource(R.drawable.arrowdown);
                }
                else {
                    btnDropdownArrow.setBackgroundResource(R.drawable.arrowup);
                }
                TextView txtName = rowView.findViewById(R.id.name);
                TextView txtTime = rowView.findViewById(R.id.txtTime);
                TextView txtDistance = rowView.findViewById(R.id.txtDistance);
                txtName.setText(values.get(position).getName());
                txtDistance.setText("distance: " + Integer.toString(values.get(position).getDistance()));
                txtTime.setText(values.get(position).getDate().toString());

                if ( values.get(position).getFollowed() ) {
                    rowView.setBackgroundColor(Color.rgb(51, 153, 255));
                }
                return rowView;

            }


        }

        public void updateData( ArrayList<MyEvent> list) {
            this.values = list;
        }

    }

