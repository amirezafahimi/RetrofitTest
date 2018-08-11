package com.example.amirreza.retrofit.post_fragment;

import com.example.amirreza.retrofit.data.model.Post;

import java.util.List;

public interface PostPresenterInterface {
    public interface PostActionListerner{
        void onLoadPost(int id);
    }
    public interface View{
        void showPostsList(List<Post> posts);
    }
}
