package com.example.amirreza.retrofit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


public class PostFragment extends Fragment {
    List<Posts> posts = new ArrayList<>();
    PostRecyclerViewAdapter adapter;
    TextView body;

    public PostFragment() {
        // Required empty public constructor

    }

    public static Fragment newInstance(User user) {
        Bundle bundle = new Bundle();
        bundle.putInt("userId", user.getId());
        PostFragment postFragment = new PostFragment();
        postFragment.setArguments(bundle);
        return postFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        final RecyclerView postRecyclerView = view.findViewById(R.id.postRecycler);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Services services = retrofit.create(Services.class);
        int user_id = getArguments().getInt("userId");
        Log.e("baba", user_id+"");
        Call<List<Posts>> repos = services.listPosts(user_id);
        repos.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.isSuccessful()){

                    posts = response.body();
                    for (Posts posts1 : posts){
                        Log.e("baba", posts1.getId().toString());
                    }
                    postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new PostRecyclerViewAdapter(getContext(), posts) ;
                    postRecyclerView.setAdapter(adapter);


                }else {
                    Log.e("Tag",response.errorBody()+"");
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
        return view;
    }
}
