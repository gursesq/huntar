package com.example.prototype.ExplorAR;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prototype.CreatAR.EventCreator;
import com.example.prototype.Event.MyEvent;
import com.example.prototype.Event.OpenEvent;
import com.example.prototype.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ExplorAR extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorar);

        Button btnCreatar = findViewById(R.id.btnEventCreator);

        btnCreatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ExplorAR.this, EventCreator.class);
                startActivity(intent);
            }
        });

        final ArrayList<MyEvent> list = new ArrayList<MyEvent>();
        list.add( new MyEvent("sa1", new Date(), 100));
        list.add( new MyEvent("sa2", new Date(), 110));
        list.add( new MyEvent("sa3", new Date(), 90));
        list.add( new MyEvent("sa4", new Date(), 170));
        list.add( new MyEvent("sa5", new Date(), 70));
        list.add( new MyEvent("sa6", new Date(), 120));
        list.add( new MyEvent("sa7", new Date(), 140));

        final ListView listView = findViewById(R.id.eventsList);
        String[] vals = new String[list.size()];
        for ( int i = 0; i < list.size(); i++ ) {
            vals[i] = " ";
        }
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter( this,list,vals, listView);
        listView.setAdapter(adapter);
        Collections.sort(list);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                makeToast("clicked: " + position);
                list.get(position).toggleOpen();
                if ( list.get(position).getOpen() ) {
                    list.add( (position+1), new OpenEvent(list.get(position),list.get(position).getFollowed()));
                    String[] vals = new String[list.size()];
                    for ( int i = 0; i < list.size(); i++ ) {
                        vals[i] = " ";
                    }
                    MySimpleArrayAdapter adapter = new MySimpleArrayAdapter( ExplorAR.this,list,vals, listView);
                    listView.setAdapter(adapter);
                    Collections.sort(list);
                }
                else {
                    System.out.println(list.get(position+1));
                    list.remove(position+1);
                    String[] vals = new String[list.size()];
                    for ( int i = 0; i < list.size(); i++ ) {
                        vals[i] = " ";
                    }
                    MySimpleArrayAdapter adapter = new MySimpleArrayAdapter( ExplorAR.this,list,vals, listView);
                    listView.setAdapter(adapter);
                    Collections.sort(list);
                }
                Collections.sort(list);
                adapter.notifyDataSetChanged();
            }
        });


    }

    public void makeToast( String message) {
        Toast.makeText( getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
