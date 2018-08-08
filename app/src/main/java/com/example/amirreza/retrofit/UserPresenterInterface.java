package com.example.amirreza.retrofit;

import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

public interface UserPresenterInterface {
    public interface UserActionListerner{
        void getUserData(List<User> users);
    }
    public interface View{
        void showUserList(List<User> users);
    }


}
