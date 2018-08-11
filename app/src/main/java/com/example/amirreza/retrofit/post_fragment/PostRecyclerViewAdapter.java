package com.example.amirreza.retrofit.post_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.data.model.Post;

import java.util.List;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {
    List<Post> posts;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public PostRecyclerViewAdapter(Context context, List<Post> posts) {
        this.mInflater = LayoutInflater.from(context);
        this.posts = posts;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String body = posts.get(position).getBody();
        holder.body.setText(body);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return posts.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView body;
        ViewHolder(View postView) {
            super(postView);
            body = postView.findViewById(R.id.body);
        }

    }
}
