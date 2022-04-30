package com.example.socialmediaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "SettingsActivity";
    private Toolbar toolbarSecondary;
    private TextView tvToolbarTitle;

    private Button btnUpdateUsername;
    private Button btnUpdateEmail;
    private Button btnUpdatePassword;
    private Button btnUpdateBio;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbarSecondary = findViewById(R.id.toolbarSecondary);

        tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        tvToolbarTitle.setText(R.string.settings);

        btnUpdateUsername = findViewById(R.id.btnUpdateUsername);
        btnUpdateEmail = findViewById(R.id.btnUpdateEmail);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);
        btnUpdateBio = findViewById(R.id.btnUpdateBio);
        btnLogOut = findViewById(R.id.btnLogOut);

        // handle back button click
        toolbarSecondary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go back to the main activity
                Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // log out the user
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}