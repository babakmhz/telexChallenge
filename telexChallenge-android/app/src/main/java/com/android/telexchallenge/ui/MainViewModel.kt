package com.android.telexchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.telexchallenge.data.RepositoryHelper
import com.android.telexchallenge.data.network.Item
import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topic
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

    private var _topicsLiveData = MutableLiveData<LiveDataWrapper<List<Item>>>()
    val topicLiveData: LiveData<LiveDataWrapper<List<Item>>> = _topicsLiveData

    //    map would point to api address and sub topics
    private var _subTopicsLiveData = MutableLiveData<LiveDataWrapper<Map<Item, List<SubTopic>>>>()
    val subTopicLiveData: LiveData<LiveDataWrapper<Map<Item, List<SubTopic>>>> = _subTopicsLiveData


    fun fetchTopics() {
        //  fetching data from remoteSource
        mUiScope.launch {
            _topicsLiveData.postValue(LiveDataWrapper.loading(null))
            withContext(Dispatchers.IO) {
                try {
                    // fetching stories from remote source
                    val result = repositoryHelper.fetchTopics()
                    _topicsLiveData.postValue(LiveDataWrapper.success(result.items))
                    fetchSubTopics(result)
                } catch (e: Exception) {
                    _topicsLiveData.postValue(LiveDataWrapper.error(e, null))
                    AppLogger.e("$e")
                }

            }
        }
    }

    fun fetchSubTopics(topic: Topic) = mUiScope.launch {

        //we can also use flow
        // note: unlimited sub items can be handled by pagination, here, i used for loop for sake of simplicity and limited items

        for (subTopic in topic.items) {
            withContext(Dispatchers.IO) {
                try {
                    _subTopicsLiveData.postValue(LiveDataWrapper.loading(subTopic.apiAddress))
                    val result = async { repositoryHelper.fetchSubTopics(subTopic.apiAddress) }
                    _subTopicsLiveData.postValue(LiveDataWrapper.success(data = mapOf(subTopic to result.await())))
                } catch (e: Exception) {
                    _topicsLiveData.postValue(LiveDataWrapper.error(e, subTopic.apiAddress))
                    AppLogger.e("$e")
                }
            }
        }
    }

}
