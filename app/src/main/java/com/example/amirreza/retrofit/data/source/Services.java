package com.example.amirreza.retrofit.data.source;

import com.example.amirreza.retrofit.data.model.Posts;
import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {
    @GET("users")
    Call<List<User>> listUsers();
    @GET("posts")
    Call<List<Posts>> listPosts(@Query("userId") int userId);
}
