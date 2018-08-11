package com.example.amirreza.retrofit.user_fragment;

import android.os.Bundle;
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
import com.example.amirreza.retrofit.adapter.UserRecyclerViewAdapter;
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

    public UsersFragment() {
        // Required empty public constructor
    }

    public void setUsers(List<User> users){
        this.users=users;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        userRecyclerView = view.findViewById(R.id.userRecycler);
        userPresenter = new UserPresenter(this,new UserRepository());
        userPresenter.onLoadUser();
        return view;
    }
    @Override
    public void showUserList(List<User> users) {
        this.users = users;
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserRecyclerViewAdapter(getContext(), users) ;
        adapter.setClickListener(UsersFragment.this);
        userRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public interface user {
        void getUser(User user);
    }

    @Override
    public void onItemClick(View view, int position) {
        ((user) getContext()).getUser(users.get(position));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}