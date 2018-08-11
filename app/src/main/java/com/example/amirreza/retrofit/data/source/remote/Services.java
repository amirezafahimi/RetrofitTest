package com.example.amirreza.retrofit.data.source.remote;

import com.example.amirreza.retrofit.data.model.Post;
import com.example.amirreza.retrofit.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {
    @GET("users")
    Call<List<User>> listUsers();
    @GET("posts")
    Call<List<Post>> listPosts(@Query("userId") int userId);
}
