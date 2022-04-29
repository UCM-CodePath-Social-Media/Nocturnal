package com.example.socialmediaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.socialmediaapp.fragments.CreatePostFragment;
import com.example.socialmediaapp.fragments.PostsFragment;
import com.example.socialmediaapp.fragments.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final Fragment postsFragment = new PostsFragment();
        final Fragment createPostFragment = new CreatePostFragment();
        final Fragment userProfileFragment = new UserProfileFragment();

        toolbar = findViewById(R.id.toolbarMain);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // set the toolbar to act as the action bar
        setSupportActionBar(toolbar);

        // add a click listener to the bottom navigation menu
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.actionHome:
                        // set to the posts fragment
                        fragment = postsFragment;
                        break;
                    case R.id.actionCreate:
                        // set to the create post fragment
                        fragment = createPostFragment;
                        break;
                    case R.id.actionProfile:
                        // set to the user profile fragment
                        fragment = userProfileFragment;
                        break;
                    default:
                        return true;
                }

                // go to the fragment that was set
                int flContainerId = R.id.flContainer;
                fragmentManager.beginTransaction().replace(flContainerId, fragment).commit();

                return true;
            }
        });

        // set the default fragment (posts fragment)
        int defaultFragmentId = R.id.actionHome;
        bottomNavigationView.setSelectedItemId(defaultFragmentId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the action bar with items
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle action bar item clicks
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                // determine which action bar item was clicked
                if (item.getItemId() == R.id.itemSettingsButton) {
                    // go to the settings page
                    Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(i);
                }

                return false;
            }
        });

        return super.onOptionsItemSelected(item);
    }
}