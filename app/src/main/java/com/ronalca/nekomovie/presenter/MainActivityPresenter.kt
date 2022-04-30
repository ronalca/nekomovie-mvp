package com.ronalca.nekomovie.presenter

import com.ronalca.nekomovie.MainContract
import com.ronalca.nekomovie.model.ApiClient

class MainActivityPresenter(private val view: MainContract.View): MainContract.Presenter {
    private val model = ApiClient()

    override suspend fun getMovieTitles(): MutableList<String> {
        return model.getMovieTitles()
    }
}