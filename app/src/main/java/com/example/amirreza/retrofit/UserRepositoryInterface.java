package com.example.amirreza.retrofit;

import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

public interface UserRepositoryInterface {
    void getUserData(UserPresenter.GetUsersInterface getUsers);
}
