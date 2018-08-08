package com.example.amirreza.retrofit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amirreza.retrofit.PostPresenter;
import com.example.amirreza.retrofit.PostPresenterInterface;
import com.example.amirreza.retrofit.PostRepository;
import com.example.amirreza.retrofit.adapter.PostRecyclerViewAdapter;
import com.example.amirreza.retrofit.data.model.Posts;
import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.data.source.Services;
import com.example.amirreza.retrofit.data.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment implements PostPresenterInterface.View {
    PostRecyclerViewAdapter adapter;
    TextView body;
    PostPresenter postPresenter;
    RecyclerView postRecyclerView;

    public PostFragment() {
        // Required empty public constructor

    }

    public static Fragment newInstance(int userId) {
        Bundle bundle = new Bundle();
        bundle.putInt("userId", userId);
        PostFragment postFragment = new PostFragment();
        postFragment.setArguments(bundle);
        return postFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        postPresenter = new PostPresenter(this,new PostRepository());
        int user_id = getArguments().getInt("userId");
        Log.e("Tag",user_id+"");
        postPresenter.getPostsData(user_id);
        postRecyclerView = view.findViewById(R.id.postRecycler);
        return view;
    }

    @Override
    public void showPostsList(List<Posts> posts) {
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostRecyclerViewAdapter(getContext(), posts) ;
        postRecyclerView.setAdapter(adapter);

    }
}
