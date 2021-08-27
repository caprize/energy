package com.example.energo_project.retrofit_interface;

import com.example.energo_project.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("list")
    Call<User> loadRepo();
    @GET("{login}")
    Call<User> getUser(@Path("login") String login);
}
