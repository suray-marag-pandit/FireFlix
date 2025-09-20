package com.example.FireFlix.retrofit.tmdbApi

import com.example.moviesapp.Retrofit.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val baseurl = "https://api.themoviedb.org/3/"

    val getApi: ApiServices by lazy {
        Retrofit.Builder().baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create()) // convert json to actual object(here gson do this)
            .build()
            .create(ApiServices::class.java)
    }
}