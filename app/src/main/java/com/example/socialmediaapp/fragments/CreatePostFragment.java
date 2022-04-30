package com.example.socialmediaapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialmediaapp.Post;
import com.example.socialmediaapp.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreatePostFragment extends Fragment {

    public static final String TAG = "CreatePostFragment";
    private EditText etPostDescription;
    private Button btnAddImage;
    private Button btnPost;

    public CreatePostFragment() {
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
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        etPostDescription = (EditText) view.findViewById(R.id.etPostDescription);
        btnAddImage = (Button) view.findViewById(R.id.btnAddImage);
        btnPost = (Button) view.findViewById(R.id.btnPost);

        // Add a click listener to the add image button
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Add a click listener to the post button
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the post description
                String postDescription = etPostDescription.getText().toString();

                // check if the post description is empty, and if so, handle accordingly
                if (postDescription.isEmpty()) {
                    Toast.makeText(getContext(), "Post description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // get the user
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(currentUser, postDescription);
            }
        });
    }

    private void savePost(ParseUser currentUser, String postDescription) {
        // create a new Post object and set its attributes
        Post post = new Post();
        post.setUser(currentUser);
        Log.i(TAG, currentUser.getUsername());
        post.setDescription(postDescription);

        // save the post to Parse
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    // display the error message
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                // go back to the posts page
                Fragment postsFragment = new PostsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContainer, postsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }
}