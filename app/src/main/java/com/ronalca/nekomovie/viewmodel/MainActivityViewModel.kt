package com.ronalca.nekomovie.viewmodel

import com.ronalca.nekomovie.model.MovieDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val model = MovieDataSource()

    // Create LiveData with a MutableList
    private val _movieLiveData = MutableLiveData<List<String>>()
    val movieLiveData: LiveData<List<String>> = _movieLiveData

    init {
        getMovieTitles()
    }

    private fun getMovieTitles() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieLiveData.postValue(model.getMovieTitles())
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}