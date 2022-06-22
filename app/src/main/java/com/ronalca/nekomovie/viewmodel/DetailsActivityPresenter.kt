package com.ronalca.nekomovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ronalca.nekomovie.DetailsContract
import com.ronalca.nekomovie.model.MovieDataSource

class DetailsActivityPresenter(private val view: DetailsContract.View): DetailsContract.Presenter {
    private val model = MovieDataSource()

    // Create LiveData with a MutableList
    private val _detailsLiveData = MutableLiveData<List<String>>()
    val detailsLiveData: LiveData<List<String>> = _detailsLiveData

    override suspend fun getMovieDetails(videoId: Int) {
        _detailsLiveData.postValue(model.getMovieDetails(videoId))
    }
}