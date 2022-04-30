package com.example.socialmediaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "SettingsActivity";
    private Toolbar toolbarSecondary;
    private TextView tvToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbarSecondary = findViewById(R.id.toolbarSecondary);

        tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        tvToolbarTitle.setText(R.string.settings);

        // handle back button click
        toolbarSecondary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go back to the main activity
                Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}