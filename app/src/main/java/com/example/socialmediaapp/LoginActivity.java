package com.example.socialmediaapp;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private EditText etUserIdentifier;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // check if the user is already logged in
        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        // get widgets from the layout
        etUserIdentifier = findViewById(R.id.etUserIdentifier);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // add a click listener to the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the user login data
                String userIdentifier = etUserIdentifier.getText().toString();
                String password = etPassword.getText().toString();

                loginUser(userIdentifier, password);
            }
        });

        // add a click listener to the sign up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to the Register activity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser(String userIdentifier, String password) {
        // check if the user entered a username or email
        if (userIdentifier.contains("@")) {
            // username entered an email, so need to get the username from the database
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("email", userIdentifier);
            query.getFirstInBackground(new GetCallback<ParseUser>() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser == null) {
                        // user not found in database
                        Toast.makeText(LoginActivity.this, e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String username = parseUser.getUsername();
                        login(username, password);
                    }
                }
            });

        } else {
            login(userIdentifier, password);
        }
    }

    private void login(String username, String password) {
        // log in with the username
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    // login successful, go to MainActivity
                    goMainActivity();
                } else {
                    // display error message
                    Toast.makeText(LoginActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
