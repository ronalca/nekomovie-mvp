package com.ronalca.nekomovie.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/"

object ApiAdapter {
    val API_CLIENT: ApiInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)
}