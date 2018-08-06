package com.example.amirreza.retrofit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UsersFragment extends Fragment implements UserRecyclerViewAdapter.ItemClickListener {
    List<User> users = new ArrayList<>();
    UserRecyclerViewAdapter adapter;
    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        final RecyclerView userRecyclerView = view.findViewById(R.id.userRecycler);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Services services = retrofit.create(Services.class);
        Call<List<User>> repos = services.listUsers();
        repos.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    users = (List<User>) response.body();
                    for (User user : users)
                        Log.e("Tag",user.getPhone()+"");
                    userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new UserRecyclerViewAdapter(getContext(), users) ;
                    adapter.setClickListener(UsersFragment.this);
                    userRecyclerView.setAdapter(adapter);
                }else {
                    Log.e("Tag",response.errorBody()+"");
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
        return view;
    }

    public interface user {
        void getUser(User name);
    }

    @Override
    public void onItemClick(View view, int position) {
        ((user) getContext()).getUser(users.get(position));
    }
}
