package com.example.amirreza.retrofit.user;

import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

public interface UserPresenterInterface {

    public interface UserActionListerner{
        void onLoadUser();
    }
    public interface View{
        void showUserList(List<User> users);

        void showProgress();

        void hideProgress();
    }


}
