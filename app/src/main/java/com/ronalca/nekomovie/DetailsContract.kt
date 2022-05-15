package com.ronalca.nekomovie

interface DetailsContract {
    interface View : MainContract {
        fun showMovieDetails(videoId: Int)
    }

    interface Presenter : MainContract {
        suspend fun getMovieDetails(videoId: Int)
    }
}