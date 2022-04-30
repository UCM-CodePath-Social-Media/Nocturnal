package com.example.socialmediaapp.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialmediaapp.Post;
import com.example.socialmediaapp.PostsAdapter;
import com.example.socialmediaapp.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserProfileFragment extends Fragment {

    public static final String TAG = "UserProfileFragment";
    private TextView tvUsernameHeading;
    private TextView tvUserJoinDate;
    private TextView tvPostsHeading;
    private RecyclerView rvPosts;
    private PostsAdapter adapter;
    private List<Post> userPosts;
    private View view;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    // This event is triggered soon after onCreateView()
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        tvUsernameHeading = view.findViewById(R.id.tvUsernameHeading);
        tvUserJoinDate = view.findViewById(R.id.tvUserJoinDate);
        rvPosts = view.findViewById(R.id.rvPosts);
        userPosts = new ArrayList<>();

        ParseUser currentUser = ParseUser.getCurrentUser();

        // set the username heading to the user's username
        tvUsernameHeading.setText(currentUser.getUsername());

        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String userJoinDate = dateFormat.format(currentUser.getCreatedAt());
        tvUserJoinDate.setText("Joined " + userJoinDate);

        // create the adapter
        adapter = new PostsAdapter(getContext(), userPosts);

        // set the adapter on the RecyclerView
        rvPosts.setAdapter(adapter);

        // set the layout manager on the RecyclerView
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }
    public void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.addDescendingOrder("createdAt");
//        query.whereContains("user", ParseUser.getCurrentUser());
        query.include("user");

        // perform the query for posts from Parse
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                // add the posts to the list and inform the adapter of the new data
                userPosts.addAll(posts);

                tvPostsHeading = view.findViewById(R.id.tvPostsHeading);
                tvPostsHeading.setText(userPosts.size() + " Posts");

                adapter.notifyDataSetChanged();
            }
        });
    }


}