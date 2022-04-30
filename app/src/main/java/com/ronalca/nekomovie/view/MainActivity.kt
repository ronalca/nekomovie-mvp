package com.ronalca.nekomovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import android.util.Log

import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.presenter.MainActivityPresenter
import com.ronalca.nekomovie.MainContract
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainContract.Presenter = MainActivityPresenter(this)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val movieLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }

    override fun showMovieTitles() {
        ioScope.launch {
            val movieTitles = presenter.getMovieTitles()

            withContext(Dispatchers.Main) {
                val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
                recyclerView.adapter = MovieAdapter(movieTitles)

                for (i in 0 until movieTitles.count()) {
                    movieLiveData.postValue(movieTitles[i])
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ioScope.cancel()
    }

}