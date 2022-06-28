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

package com.ronalca.nekomovie.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ronalca.nekomovie.data.MovieDataSource
import com.ronalca.nekomovie.presentation.moviedetail.MovieDetailContract

class MovieDetailPresenter(private val view: MovieDetailContract.View): MovieDetailContract.Presenter {
    private val model = MovieDataSource()

    private val _detailsLiveData = MutableLiveData<List<String>>()
    val detailsLiveData: LiveData<List<String>> = _detailsLiveData

    override suspend fun getMovieDetails(videoId: Int) {
        _detailsLiveData.postValue(model.getMovieDetails(videoId))
    }
}