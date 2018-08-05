package com.example.amirreza.retrofit;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        this.user = user;
        CommentFragment commentFragment = new CommentFragment(this.user);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, commentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
