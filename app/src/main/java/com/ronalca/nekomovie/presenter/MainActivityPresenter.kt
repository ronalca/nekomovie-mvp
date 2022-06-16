package com.ronalca.nekomovie.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ronalca.nekomovie.MainContract
import com.ronalca.nekomovie.model.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityPresenter(private val view: MainContract.View): MainContract.Presenter {
    private val model = ApiClient()

    // TODO: implement lifecycle-aware presenter instead of livedata
    // Create LiveData with a MutableList
    private val _movieLiveData = MutableLiveData<List<String>>()
    val movieLiveData: LiveData<List<String>> = _movieLiveData

    override suspend fun getMovieTitles() {
        withContext(Dispatchers.Main) {
            view.showMovieTitles(model.getMovieTitles())
        }
    }

    // TODO: cancel ioScope when presenter was disposed
}