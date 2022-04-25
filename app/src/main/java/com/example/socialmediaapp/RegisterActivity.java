package com.example.socialmediaapp;

import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    }
}