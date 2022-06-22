package com.ronalca.nekomovie.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Response

private const val BASE_URL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/"

/**
 * A public interface that exposes the [getApiData] method
 */
interface GoogleApiService {
    @GET("videos-enhanced-c.json")
    suspend fun getApiData(): Response<MovieResponse>
}

object GoogleApiAdapter {
    val API_CLIENT: GoogleApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GoogleApiService::class.java)
}

