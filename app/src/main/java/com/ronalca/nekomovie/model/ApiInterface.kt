package com.ronalca.nekomovie.model

import retrofit2.http.GET
import retrofit2.Response

interface ApiInterface {
    @GET("videos-enhanced-c.json")
    suspend fun getApiData(): Response<ApiResponse>
}