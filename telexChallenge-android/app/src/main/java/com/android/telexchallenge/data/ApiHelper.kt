package com.android.telexchallenge.data

import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topic

interface ApiHelper {
    suspend fun fetchTopics(): Topic
    suspend fun fetchSubTopics(apiAddress: String): List<SubTopic>
}