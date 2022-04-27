package com.ronalca.nekomovie

class DetailsContract {
    interface View : MainContract {
        fun showMovieDetails(videoId: Int)
    }

    interface Presenter : MainContract {
        suspend fun getMovieDetails(videoId: Int) : Array<String>
    }
}