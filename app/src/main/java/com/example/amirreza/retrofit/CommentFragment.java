package com.example.amirreza.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CommentFragment extends Fragment {
    User user;
    List<Comment> comments = new ArrayList<>();
    TextView body;

    public CommentFragment() {
        // Required empty public constructor

    }

    @SuppressLint("ValidFragment")
    public CommentFragment(User user) {
        this.user=user;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_comment, container, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Services services = retrofit.create(Services.class);
        Call<List<Comment>> repos = services.listComments();
        repos.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    comments = (List<Comment>) response.body();
                    for (Comment comment : comments)
                        if (comment.getId()==user.getId()) {
                            String body = comment.getBody();
                            CommentFragment.this.body = view.findViewById(R.id.body);
                            CommentFragment.this.body.setText(body);
                        }

                }else {
                    Log.e("Tag",response.errorBody()+"");
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
        return view;
    }
}
