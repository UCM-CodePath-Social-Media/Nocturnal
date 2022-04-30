package com.example.socialmediaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the post layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);

        // wrap the view inside a ViewHolder and return it
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        // get the current post
        Post post = posts.get(position);

        // bind the post to the ViewHolder
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvPostDate;
        private TextView tvDescription;
        private Button btnLike;
        private Button btnComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvPostDate = itemView.findViewById(R.id.tvPostDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);
        }

        private void bind(Post post) {
            // bind the post data to the view elements
            tvUsername.setText(post.getUser().getUsername());

            DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy h:mm a");
            String postDateString = dateFormat.format(post.getCreatedAt());
            tvPostDate.setText("Posted " + postDateString);

            tvDescription.setText(post.getDescription());

            // load the post image (if exists) into the image view
            if (post.getImage() == null) {
                ivImage.setVisibility(View.GONE);
            } else {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }

        }
    }

}
