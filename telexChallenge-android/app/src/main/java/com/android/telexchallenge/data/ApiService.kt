package com.android.telexchallenge.data

import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topic
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET(".")
    suspend fun getTopics(): Topic

    @GET
    suspend fun getSubTopic(@Url apiAddress: String): List<SubTopic>
}