package com.ronalca.nekomovie

interface MainContract {
    interface View : MainContract {
        fun showMovieTitles()
        //fun showMovieDetails()
    }

    interface Presenter : MainContract {
        suspend fun getMovieTitles() : Array<String>
    }
}