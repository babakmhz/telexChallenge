package com.android.telexchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.telexchallenge.R
import com.android.telexchallenge.data.network.Item
import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.utils.AppLogger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val mainRecyclerAdapter: MainRecyclerAdapter by lazy {
        MainRecyclerAdapter(this, arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchTopics()
        setUpTopicsObserver()
        setUpSubTopicsObserver()
    }

    private fun setUpTopicsObserver() {
        viewModel.topicLiveData.observe(this, {
            // TODO: 2/19/21 handle liveDataWrapper state
            // replacing progress bar with titles and recycler views
            if (it.response != null) {
                initiateRecyclerView(it.response)
            }
        })
    }

    private fun initiateRecyclerView(items: List<Item>) {
        progress.visibility = View.GONE
        recycler_main.visibility = View.VISIBLE
        recycler_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainRecyclerAdapter.setTopicData(items as ArrayList<Item>)
        recycler_main.adapter = mainRecyclerAdapter
    }

    private fun initiateLoading() {
        recycler_main.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    private fun setUpSubTopicsObserver() {
        viewModel.subTopicLiveData.observe(this, {
            // TODO: 2/19/21 handle liveDataWrapper state
            AppLogger.i("subTopicsMap ${it.response}")
            if (it.response != null && mainRecyclerAdapter.getCurrentData().isNotEmpty()) {
                val response = it.response
                val childedData = mutableMapOf<Item, SubTopicAdapter>()
                response.forEach { (key, value) ->
                    childedData[key] = SubTopicAdapter(this, value as ArrayList<SubTopic>)
                }
                mainRecyclerAdapter.setAdapters(childedData)
            }
        })
    }

}