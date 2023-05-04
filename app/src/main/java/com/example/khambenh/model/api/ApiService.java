package com.example.khambenh.model.api;

import com.example.khambenh.model.adapter.ContentDoctor;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("doctors")
    Call<ContentDoctor> getAllDoctor();
}
