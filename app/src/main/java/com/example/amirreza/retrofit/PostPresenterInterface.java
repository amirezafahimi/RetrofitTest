package com.example.amirreza.retrofit;

import com.example.amirreza.retrofit.data.model.Posts;
import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

public interface PostPresenterInterface {
    public interface PostActionListerner{
        void getPostsData(int id);
    }
    public interface View{
        void showPostsList(List<Posts> posts);
    }
}
