package com.example.amirreza.retrofit;


import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

public class UserPresenter implements UserPresenterInterface.UserActionListerner{


    private UserPresenterInterface.View userViewListener;
    private UserRepository userRepository;

    public UserPresenter(UserPresenterInterface.View userViewListener, UserRepository userRepository) {
        this.userViewListener = userViewListener;
        this.userRepository = userRepository;
    }

    @Override
    public void getUserData() {
        userRepository.getUserData(new GetUsers());

    }

    private class GetUsers implements GetUsersInterface {

        @Override
        public void loadUserData(List<User> users) {
            userViewListener.showUserList(users);
        }
    }

    interface GetUsersInterface{

        void loadUserData(List<User> users);
    }
}
