package com.ronalca.nekomovie.model

import android.util.Log

class ApiClient {

    suspend fun getMovieTitles(): MutableList<String> {
        val movieList: MutableList<String> = mutableListOf()

        try {
            val apiResponse = ApiAdapter.API_CLIENT.getApiData()

            if (apiResponse.isSuccessful) {
                val items = apiResponse.body()?.categories

                // re-implement here using flatmap method!

                if (items != null) {
                    for (i in 0 until items.count()) {
                        val videoSze = items[i].videos.size

                        for (j in 0 until videoSze) {
                            val title = items[i].videos[j].title.toString()
                            Log.d("MovieTitle", title)
                            movieList += title
                        }
                    }
                }

            }
            else {
                Log.e("RETROFIT_ERROR!", apiResponse.code().toString())
            }
        }
        catch (e:Exception) {
            Log.e("EXCEPTION!", e.message.toString())
        }

        return movieList
    }

    suspend fun getMovieDetails(videoId: Int) : MutableList<String> {
        val movieDetails: MutableList<String> = mutableListOf()

        try {
            val apiResponse = ApiAdapter.API_CLIENT.getApiData()

            if (apiResponse.isSuccessful) {
                val items = apiResponse.body()?.categories

                if (items != null) {
                    for (i in 0 until items.count()) {
                        val movieTitle = items[i].videos[videoId].title.toString()
                        movieDetails += movieTitle
                        Log.d("MovieTitle: ", movieTitle)

                        val movieSubtitle = items[i].videos[videoId].subtitle.toString()
                        movieDetails += movieSubtitle
                        Log.d("MovieTitle", movieSubtitle)

                        val movieSource = items[i].videos[videoId].sources.toString()
                        movieDetails += movieSource
                        Log.d("MovieTitle", movieSource)

                        val movieThumb = items[i].videos[videoId].thumb.toString()
                        movieDetails += movieThumb
                        Log.d("MovieTitle", movieThumb)

                        val movieImage480x270 = items[i].videos[videoId].image480x270.toString()
                        movieDetails += movieImage480x270
                        Log.d("MovieTitle", movieImage480x270)

                        val movieImage780x1200 = items[i].videos[videoId].image780x1200.toString()
                        movieDetails += movieImage780x1200
                        Log.d("MovieTitle", movieImage780x1200)

                        val movieStudio = items[i].videos[videoId].studio.toString()
                        movieDetails += movieStudio
                        Log.d("MovieTitle", movieStudio)
                    }
                }
            }
            else {
                Log.e("RETROFIT_ERROR!", apiResponse.code().toString())
            }
        }
        catch (e:Exception) {
            Log.e("EXCEPTION!", e.message.toString())
        }

        return movieDetails
    }
}
