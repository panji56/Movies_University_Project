package com.example.movies.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallAPI {
    private static final String BASE_URL="http://www.omdbapi.com";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit==null){
//            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    };

}
