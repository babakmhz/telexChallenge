package com.android.telexchallenge.data.network

import com.google.gson.annotations.SerializedName

data class Topic(@SerializedName("items") val items: List<Item>)

data class Item(@SerializedName("title") val title: String,
                @SerializedName("view_type") val viewType: String,
                @SerializedName("api_address") val apiAddress: String)
