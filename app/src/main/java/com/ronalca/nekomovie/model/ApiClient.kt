package com.ronalca.nekomovie.model

import android.util.Log

class ApiClient {

    suspend fun getMovieTitles() : Array<String> {
        var movieTitles: Array<String> = emptyArray()

        try {
            val apiResponse = ApiAdapter.API_CLIENT.getApiData()

            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                val items = apiResponse.body()?.categories

                if (items != null) {
                    for (i in 0 until items.count()) {
                        var videoSze = items[i].videos.size

                        for (j in 0 until videoSze) {
                            var title = items[i].videos[j].title.toString()
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

    suspend fun getMovieDetails(videoId: Int) : Array<String> {
        var movieDetails: Array<String> = emptyArray()

        return movieDetails
    }
}
