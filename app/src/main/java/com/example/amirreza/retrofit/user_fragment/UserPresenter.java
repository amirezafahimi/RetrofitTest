package com.example.amirreza.retrofit.user_fragment;


import com.example.amirreza.retrofit.data.model.User;
import com.example.amirreza.retrofit.data.UserRepository;

import java.util.List;

public class UserPresenter implements UserPresenterInterface.UserActionListerner{


    private UserPresenterInterface.View userViewListener;
    private UserRepository userRepository;

    public UserPresenter(UserPresenterInterface.View userViewListener, UserRepository userRepository) {
        this.userViewListener = userViewListener;
        this.userRepository = userRepository;
    }

    @Override
    public void onLoadUser() {
        userViewListener.showProgress();
        userRepository.getUserData(new GetUsers());

    }

    private class GetUsers implements GetUsersInterface {

        @Override
        public void loadUserData(List<User> users) {
            userViewListener.hideProgress();
            userViewListener.showUserList(users);
        }
    }

    public interface GetUsersInterface{

        void loadUserData(List<User> users);
    }
}
