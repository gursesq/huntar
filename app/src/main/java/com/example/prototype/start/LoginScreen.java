package com.example.prototype.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototype.ExplorAR.ExplorAR;
import com.example.prototype.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "LoginScreen";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(LoginScreen.this);
        mAuth = FirebaseAuth.getInstance();


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnGuestLogin = (Button) findViewById(R.id.btnGuestLogin);
        Button btnSignup = (Button) findViewById(R.id.btnSignup);
        Button btnHelpLogin = findViewById(R.id.btnHint);


        final EditText txtMail = (EditText) findViewById(R.id.txtMail);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        TextView txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);

        btnHelpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast("help clicked");
                Intent intent = new Intent( LoginScreen.this, HelpLogin.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtMail.getText().toString();
                String password = txtPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Intent intent = new Intent(LoginScreen.this, ExplorAR.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

        btnGuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginScreen.this, ExplorAR.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginScreen.this, SignupScreen.class);
                startActivity(intent);
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onStart( ) {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI( FirebaseUser  user) {


    }

    public void makeToast( String message) {
        Toast.makeText( getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
