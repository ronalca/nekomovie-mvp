package com.ronalca.nekomovie.model

import android.util.Log

class ApiClient {

    suspend fun getMovieTitles() : MutableList<String> {
        val movieTitles: MutableList<String> = mutableListOf()

        try {
            val apiResponse = ApiAdapter.API_CLIENT.getApiData()

            if (apiResponse.isSuccessful) {
                val items = apiResponse.body()?.categories

                if (items != null) {
                    for (i in 0 until items.count()) {
                        val videoSze = items[i].videos.size

                        for (j in 0 until videoSze) {
                            val title = items[i].videos[j].title.toString()
                            Log.d("MovieTitle", title)
                            movieTitles += title
                        }
                    }
                } else {
                    Log.e("RETROFIT_ERROR!", apiResponse.code().toString())
                }
            }
        }
        catch (e:Exception) {
            Log.e("EXCEPTION!", e.message.toString())
        }
        return movieTitles
    }

    suspend fun getMovieDetails(videoId: Int) : MutableList<String> {
        var movieDetails: MutableList<String> = mutableListOf()

        return movieDetails
    }
}
