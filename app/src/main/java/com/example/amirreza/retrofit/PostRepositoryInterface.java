package com.example.amirreza.retrofit;

import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

interface PostRepositoryInterface {
    void getPostsData(int id, PostPresenter.GetPostsInterface getPosts);
}
