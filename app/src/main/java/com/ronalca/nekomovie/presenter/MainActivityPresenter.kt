package com.ronalca.nekomovie.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ronalca.nekomovie.MainContract
import com.ronalca.nekomovie.model.ApiClient

class MainActivityPresenter(private val view: MainContract.View): MainContract.Presenter {
    private val model = ApiClient()

    // Create LiveData with a MutableList
    private val _movieLiveData = MutableLiveData<MutableList<String>>()
    val movieLiveData: LiveData<MutableList<String>> = _movieLiveData

    override suspend fun getMovieTitles() {
        _movieLiveData.postValue(model.getMovieTitles())
    }
}