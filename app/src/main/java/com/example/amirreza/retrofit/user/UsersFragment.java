package com.example.amirreza.retrofit.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.data.UserRepository;
import com.example.amirreza.retrofit.data.model.User;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment implements UserRecyclerViewAdapter.ItemClickListener,
        UserPresenterInterface.View {

    RecyclerView userRecyclerView;
    List<User> users = new ArrayList<>();
    ProgressBar progressBar;
    UserPresenter userPresenter;
    //UserPresenterInterface userPresenterInterface;
    UserRecyclerViewAdapter adapter;

    public static Fragment getInstance() {
        UsersFragment fragment = new UsersFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userRecyclerView = view.findViewById(R.id.userRecycler);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userPresenter = new UserPresenter(this, new UserRepository());
        userPresenter.onLoadUser();
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserRecyclerViewAdapter(getContext());
        adapter.setClickListener(UsersFragment.this);
        userRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showUserList(List<User> users) {
        this.users = users;
        adapter.setData(users);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(View view, int position) {
        ((user) getContext()).getUser(users.get(position));
    }

    public interface user {
        void getUser(User user);
    }
}
