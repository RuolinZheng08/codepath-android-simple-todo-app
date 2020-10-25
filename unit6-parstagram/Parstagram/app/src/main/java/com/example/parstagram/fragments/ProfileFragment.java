package com.example.parstagram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.parstagram.LoginActivity;
import com.example.parstagram.Post;
import com.example.parstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                // navigate back to login screen
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER); // each post includes the user who created the post
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(MAX_POSTS);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Toast.makeText(getContext(), e.getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),
                            String.format("Fetched %d posts", posts.size()),
                            Toast.LENGTH_SHORT).show();
                    allPosts.addAll(posts);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
