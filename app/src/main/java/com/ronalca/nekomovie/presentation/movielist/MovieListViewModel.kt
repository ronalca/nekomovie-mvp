/*
 * Copyright 2022 Rony Alcala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ronalca.nekomovie.presentation.movielist

import com.ronalca.nekomovie.data.MovieDataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieListViewModel: ViewModel() {
    private val model = MovieDataSource()

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