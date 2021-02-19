package com.android.telexchallenge.data

import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topic
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
        private val apiHelper: ApiHelper
) : RepositoryHelper {
    override suspend fun fetchTopics(): Topic {
        return apiHelper.fetchTopics()
    }

    override suspend fun fetchSubTopics(apiAddress: String): List<SubTopic> {
        return apiHelper.fetchSubTopics(apiAddress)
    }

}