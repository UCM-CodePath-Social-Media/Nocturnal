package com.example.socialmediaapp;

import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";

    private EditText etUsername;
    private EditText etEmail;
    private EditText etDateOfBirth;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // get the widgets from the layout
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnSignIn = findViewById(R.id.btnSignIn);

        // add a click listener to the register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String dateOfBirth = etDateOfBirth.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                // check if the password matches
                if (!password.equals(confirmPassword)) {
                    // display error message
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.passwords_dont_match),
                            Toast.LENGTH_SHORT).show();
                }

                User user = new User(username, email, dateOfBirth, password);
                registerUser(user);
            }
        });

        // add a click listener to the sign in button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to Login activity
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser(User user) {
        // TODO: register the user
        ParseUser parseUser = new ParseUser();

        parseUser.setUsername(user.getUsername());
        parseUser.setPassword(user.getPassword());
        parseUser.setEmail(user.getEmail());

        Log.i(TAG, user.getUsername());
        Log.i(TAG, user.getEmail());

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    String errorMessage = e.getMessage();

                    ParseUser.logOut();
                    Toast.makeText(RegisterActivity.this, errorMessage,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}