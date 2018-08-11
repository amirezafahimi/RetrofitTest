package com.example.amirreza.retrofit.data;

import android.util.Log;

import com.example.amirreza.retrofit.data.model.User;
import com.example.amirreza.retrofit.data.source.remote.Client;
import com.example.amirreza.retrofit.user.UserPresenter;
import com.example.amirreza.retrofit.data.source.remote.Services;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    List<User> users = new ArrayList<>();

    public void getUserData(final UserPresenter.GetUsersInterface mInterface) {

        Client.getRetrofitInstance().create(Services.class).listUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    users = response.body();
                    for (User user : users)
                        Log.e("Tag",user.getPhone()+"");
                    mInterface.loadUserData(users);

                }else {
                    Log.e("Tag",response.errorBody()+"");
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
