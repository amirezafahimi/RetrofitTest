package com.example.amirreza.retrofit;

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
