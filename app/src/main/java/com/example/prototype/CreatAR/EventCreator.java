package com.example.prototype.CreatAR;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.prototype.Event.ARPoint;
import com.example.prototype.ExplorAR.ExplorAR;
import com.example.prototype.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventCreator extends AppCompatActivity {

    private static final String TAG = "EventCreator";

    //properties
    final static ArrayList<ARPoint> list = new ArrayList<ARPoint>();

    //firebase properties
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creator);

        //initialize firebase properties


        //initialize buttons
        Button btnExplorar = findViewById(R.id.btnExplorar);
        btnExplorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventCreator.this, ExplorAR.class);
                startActivity(intent);
            }
        });

        Button btnPublish = findViewById(R.id.btnPublish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Attempting to add event to database");
                FirebaseUser user = mAuth.getCurrentUser();

            }
        });


        //initialize list
        list.add( new ARPoint("sa1"));
        list.add( new ARPoint("sa2"));
        list.add( new ARPoint("sa3"));
        list.add( new ARPoint("sa4"));

        ListView listView = findViewById(R.id.pointsList);
        String[] vals = new String[list.size()];
        for ( int i = 0; i < list.size(); i++ ) {
            vals[i] = " ";
        }
        CreatorAdapter adapter = new CreatorAdapter(this,list,vals, listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    public static void remove( int position) {
        list.remove(position);
    }
}
