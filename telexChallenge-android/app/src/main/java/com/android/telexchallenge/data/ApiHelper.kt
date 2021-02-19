package com.android.telexchallenge.data

import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topics

interface ApiHelper {
    suspend fun fetchTopics(): Topics
    suspend fun fetchSubTopics(apiAddress: String): List<SubTopic>
}