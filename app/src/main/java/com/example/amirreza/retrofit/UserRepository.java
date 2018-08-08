package com.example.amirreza.retrofit;

import android.util.Log;

import com.example.amirreza.retrofit.data.model.User;
import com.example.amirreza.retrofit.data.source.Services;
import com.example.amirreza.retrofit.fragment.UsersFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository implements UserRepositoryInterface{
    List<User> users = new ArrayList<>();

    @Override
    public void getUserData(final UserPresenter.GetUsersInterface mInterface) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Services services = retrofit.create(Services.class);
        Call<List<User>> repos = services.listUsers();
        repos.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    users = (List<User>) response.body();
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
