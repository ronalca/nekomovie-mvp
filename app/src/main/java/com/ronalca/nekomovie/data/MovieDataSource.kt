/*
 * Copyright 2022 Rony Alcala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ronalca.nekomovie.data

import android.util.Log

class MovieDataSource {

    suspend fun getMovieTitles(): List<String> {
        val movieList: MutableList<String> = mutableListOf()

        try {
            val apiResponse = GoogleApiAdapter.API_CLIENT.getApiData()

            if (apiResponse.isSuccessful) {
                val items = apiResponse.body()?.categories

                for (i in 0 until items!!.count()) {
                    val videoSze = items[i].videos.size

                    for (j in 0 until videoSze) {
                        val title = items[i].videos[j].title.toString()
                        movieList += title
                    }
                }
            }
            else {
                Log.e("API_ERROR!", apiResponse.code().toString())
            }
        }
        catch (e:Exception) {
            Log.e("EXCEPTION!", e.message.toString())
        }

        return movieList
    }

    suspend fun getMovieDetails(videoId: Int) : List<String> {
        val movieDetails: MutableList<String> = mutableListOf()

        try {
            val apiResponse = GoogleApiAdapter.API_CLIENT.getApiData()

            if (apiResponse.isSuccessful) {
                val items = apiResponse.body()?.categories

                for (i in 0 until items!!.count()) {
                    val movieTitle = items[i].videos[videoId].title.toString()
                    movieDetails += movieTitle

                    val movieSubtitle = items[i].videos[videoId].subtitle.toString()
                    movieDetails += movieSubtitle

                    val movieSource = items[i].videos[videoId].sources.toString()
                    movieDetails += movieSource

                    val movieThumb = items[i].videos[videoId].thumb.toString()
                    movieDetails += movieThumb

                    val movieImage480x270 = items[i].videos[videoId].image480x270.toString()
                    movieDetails += movieImage480x270

                    val movieImage780x1200 = items[i].videos[videoId].image780x1200.toString()
                    movieDetails += movieImage780x1200

                    val movieStudio = items[i].videos[videoId].studio.toString()
                    movieDetails += movieStudio
                }
            }
            else {
                Log.e("API_ERROR!", apiResponse.code().toString())
            }
        }
        catch (e:Exception) {
            Log.e("EXCEPTION!", e.message.toString())
        }

        return movieDetails
    }
}