package com.ronalca.nekomovie

interface MainContract {
    interface View : MainContract {
        fun showMovieTitles(movieList: List<String>)
    }

    interface Presenter : MainContract {
        suspend fun getMovieTitles()
    }
} 