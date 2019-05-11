package com.example.prototype.start;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prototype.R;

public class MainActivity extends AppCompatActivity {
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //to login screen
        Intent intent = new Intent( MainActivity.this, LoginScreen.class);
        startActivity(intent);
    }
}
