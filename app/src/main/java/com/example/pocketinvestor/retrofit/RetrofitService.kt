package com.example.pocketinvestor.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService() {
    private lateinit var retrofit:Retrofit;

    init {
        initalizeRetrofit()
    }
    private fun initalizeRetrofit() {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("http://192.168.0.175:8080")
            .build();
    }
    public fun getRetrofit():Retrofit{
        return retrofit;
    }
}