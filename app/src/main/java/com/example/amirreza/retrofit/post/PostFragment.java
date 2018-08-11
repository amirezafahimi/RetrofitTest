package com.example.amirreza.retrofit.post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amirreza.retrofit.data.PostRepository;
import com.example.amirreza.retrofit.data.model.Post;
import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.data.model.User;

import java.util.List;


public class PostFragment extends Fragment implements PostPresenterInterface.View {
    PostRecyclerViewAdapter adapter;
    TextView body;
    PostPresenter postPresenter;
    RecyclerView postRecyclerView;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postRecyclerView = view.findViewById(R.id.postRecycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postPresenter = new PostPresenter(this,new PostRepository());
        postPresenter.onLoadPost(getArguments().getInt("userId"));
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostRecyclerViewAdapter(getContext()) ;
        postRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showPostsList(List<Post> posts) {
        adapter.setData(posts);
    }
}
