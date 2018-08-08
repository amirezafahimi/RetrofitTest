package com.example.amirreza.retrofit.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.amirreza.retrofit.fragment.PostFragment;
import com.example.amirreza.retrofit.R;
import com.example.amirreza.retrofit.data.model.User;
import com.example.amirreza.retrofit.fragment.UsersFragment;

public class MainActivity extends AppCompatActivity implements UsersFragment.user {

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsersFragment usersFragment = new UsersFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, usersFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void getUser(User user) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, PostFragment.newInstance(user));
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
