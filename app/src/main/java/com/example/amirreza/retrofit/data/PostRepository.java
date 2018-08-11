package com.example.amirreza.retrofit.data;

import android.util.Log;

import com.example.amirreza.retrofit.data.model.Post;
import com.example.amirreza.retrofit.data.source.remote.Client;
import com.example.amirreza.retrofit.post.PostPresenter;
import com.example.amirreza.retrofit.data.source.remote.Services;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    List<Post> posts = new ArrayList<>();

    public void getPostsData(int id, final PostPresenter.GetPostsInterface getPosts) {


        Client.getRetrofitInstance().create(Services.class).listPosts(id).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){

                    posts = response.body();
                    for (Post posts1 : posts){
                        Log.e("baba", posts1.getId().toString());
                        getPosts.loadPostData(posts);
                    }
                }else {
                    Log.e("Tag",response.errorBody()+"");
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

}
