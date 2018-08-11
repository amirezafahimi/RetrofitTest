package com.example.amirreza.retrofit.post_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amirreza.retrofit.data.PostRepository;
import com.example.amirreza.retrofit.adapter.PostRecyclerViewAdapter;
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
        postPresenter = new PostPresenter(this,new PostRepository());
        int user_id = getArguments().getInt("userId");
        Log.e("Tag",user_id+"");
        postPresenter.onLoadPost(user_id);
        postRecyclerView = view.findViewById(R.id.postRecycler);
        return view;
    }

    @Override
    public void showPostsList(List<Post> posts) {
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostRecyclerViewAdapter(getContext(), posts) ;
        postRecyclerView.setAdapter(adapter);

    }
}
