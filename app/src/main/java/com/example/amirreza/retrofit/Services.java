package com.example.amirreza.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {
    @GET("users")
    Call<List<User>> listUsers();
    @GET("comments?postId=1")
    Call<List<Comment>> listComments();
}
