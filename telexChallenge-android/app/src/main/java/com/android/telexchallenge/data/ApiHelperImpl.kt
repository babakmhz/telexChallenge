package com.android.telexchallenge.data

import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topic
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun fetchTopics(): Topic {
        return apiService.getTopics()
    }

    override suspend fun fetchSubTopics(apiAddress: String): List<SubTopic> {
        return apiService.getSubTopic(apiAddress)
    }
}