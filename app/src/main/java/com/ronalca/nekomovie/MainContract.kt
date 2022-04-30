package com.ronalca.nekomovie

interface MainContract {
    interface View : MainContract {
        fun showMovieTitles()
    }

    interface Presenter : MainContract {
        suspend fun getMovieTitles() : MutableList<String>
    }
}