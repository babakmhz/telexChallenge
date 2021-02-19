package com.android.telexchallenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.telexchallenge.R
import com.android.telexchallenge.utils.AppLogger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchTopics()
        setUpTopicsObserver()
        setUpSubTopicsObserver()
    }

    private fun setUpTopicsObserver() {
        viewModel.topicsLiveData.observe(this, Observer {
            // here replace progress bar with titles and recycler views
        })
    }

    private fun setUpSubTopicsObserver() {
        viewModel.subTopicLiveData.observe(this, Observer {
            if (it.response != null)
                AppLogger.i("subTopicsMap ${it.response}")
        })
    }
}