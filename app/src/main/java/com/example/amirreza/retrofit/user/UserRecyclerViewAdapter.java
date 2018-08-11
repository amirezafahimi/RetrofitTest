package com.example.amirreza.retrofit.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {
    private List<User> users;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public UserRecyclerViewAdapter(Context context, List<User> users) {
        this.mInflater = LayoutInflater.from(context);
        this.users = users;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = users.get(position).getName();
        String phoneNumber = users.get(position).getPhone();
        holder.name.setText(name);
        holder.phoneNumber.setText(phoneNumber);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return users.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView phoneNumber;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.e("Tag",users.size()+" "+getAdapterPosition());
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}