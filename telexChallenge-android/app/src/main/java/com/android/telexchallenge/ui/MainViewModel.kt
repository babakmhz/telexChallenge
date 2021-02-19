package com.android.telexchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.telexchallenge.data.RepositoryHelper
import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topics
import com.android.telexchallenge.utils.AppLogger
import com.android.telexchallenge.utils.LiveDataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val repositoryHelper: RepositoryHelper
) : ViewModel() {

    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)

    private var _topicsLiveData = MutableLiveData<LiveDataWrapper<Topics>>()
    val topicsLiveData: LiveData<LiveDataWrapper<Topics>> = _topicsLiveData

    //    map would point to api address and sub topics
    private var _subTopicsLiveData = MutableLiveData<LiveDataWrapper<Map<String, List<SubTopic>>>>()
    val subTopicLiveData: LiveData<LiveDataWrapper<Map<String, List<SubTopic>>>> = _subTopicsLiveData


    fun fetchTopics() {
        //  fetching data from remoteSource
        mUiScope.launch {
            _topicsLiveData.postValue(LiveDataWrapper.loading(null))
            withContext(Dispatchers.IO) {
                try {
                    // fetching stories from remote source
                    val result = repositoryHelper.fetchTopics()
                    _topicsLiveData.postValue(LiveDataWrapper.success(result))
                    fetchSubTopics(result)
                } catch (e: Exception) {
                    _topicsLiveData.postValue(LiveDataWrapper.error(e, null))
                    AppLogger.e("$e")
                }

            }
        }
    }

    private fun fetchSubTopics(topics: Topics) = mUiScope.launch {
//        we can also use flow
        for (topic in topics.items) {
            withContext(Dispatchers.IO) {
                try {
                    _subTopicsLiveData.postValue(LiveDataWrapper.loading(topic.apiAddress))
                    val result = async { repositoryHelper.fetchSubTopics(topic.apiAddress) }
                    _subTopicsLiveData.postValue(LiveDataWrapper.success(data = mapOf(topic.apiAddress to result.await())))
                } catch (e: Exception) {
                    _topicsLiveData.postValue(LiveDataWrapper.error(e, topic.apiAddress))
                    AppLogger.e("$e")
                }
            }
        }
    }

}
