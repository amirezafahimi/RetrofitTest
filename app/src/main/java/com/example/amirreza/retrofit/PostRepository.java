package com.example.amirreza.retrofit;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.amirreza.retrofit.adapter.PostRecyclerViewAdapter;
import com.example.amirreza.retrofit.data.model.Posts;
import com.example.amirreza.retrofit.data.source.Services;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository implements PostRepositoryInterface{

    List<Posts> posts = new ArrayList<>();

    @Override
    public void getPostsData(int id, final PostPresenter.GetPostsInterface getPosts) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Services services = retrofit.create(Services.class);
        Call<List<Posts>> repos = services.listPosts(id);
        repos.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.isSuccessful()){

                    posts = response.body();
                    for (Posts posts1 : posts){
                        Log.e("baba", posts1.getId().toString());
                        getPosts.loadPostData(posts);
                    }
                }else {
                    Log.e("Tag",response.errorBody()+"");
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }

}
