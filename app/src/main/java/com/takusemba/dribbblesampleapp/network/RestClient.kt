package com.takusemba.dribbblesampleapp.network

import com.google.gson.Gson

import retrofit.RestAdapter
import retrofit.converter.GsonConverter


object RestClient {

    val restAdapter: RestAdapter
        get() = RestAdapter
                .Builder()
                .setEndpoint("https://api.dribbble.com/v1/")
                .setConverter(GsonConverter(Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
}