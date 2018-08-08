package com.example.amirreza.retrofit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amirreza.retrofit.PostPresenter;
import com.example.amirreza.retrofit.PostRepository;
import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.UserPresenter;
import com.example.amirreza.retrofit.UserRepository;
import com.example.amirreza.retrofit.adapter.UserRecyclerViewAdapter;
import com.example.amirreza.retrofit.data.model.User;
import com.example.amirreza.retrofit.UserPresenterInterface;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment implements UserRecyclerViewAdapter.ItemClickListener,
        UserPresenterInterface.View {

    RecyclerView userRecyclerView;
    List<User> users = new ArrayList<>();
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
        userRecyclerView = view.findViewById(R.id.userRecycler);
        userPresenter = new UserPresenter(this,new UserRepository());
        userPresenter.getUserData();
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
