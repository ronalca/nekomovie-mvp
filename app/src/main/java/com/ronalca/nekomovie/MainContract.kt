package com.ronalca.nekomovie

interface MainContract {
    interface View : MainContract {
        fun showMovieTitles()
        fun showMovieDetails(videoId: Int)
    }

    interface Presenter : MainContract {
        suspend fun getMovieTitles() : Array<String>
        suspend fun getMovieDetails(videoId: Int) : Array<String>
    }
}