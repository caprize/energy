package com.example.energo_project.retrofit_interface;

import com.example.energo_project.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("list")
    Call<User> loadRepo();
    @GET("user/{id}")
    Call<User> getUser();
}
