package com.example.petstore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface PetAPI {
    @GET("/pet/{petId}")
    Call<List<Pet>> getPet(@Path("id") int id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
