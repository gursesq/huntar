package com.example.prototype.HuntAR;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.prototype.R;

public class MapsActivity extends AppCompatActivity {

    private static final String TAG = "MapsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button btnGoToARActivity = findViewById(R.id.btnGoToARActivity);
        btnGoToARActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button Clicked");
                Intent intent = new Intent(MapsActivity.this, ARActivity.class);
                startActivity(intent);
            }
        });
    }
}
