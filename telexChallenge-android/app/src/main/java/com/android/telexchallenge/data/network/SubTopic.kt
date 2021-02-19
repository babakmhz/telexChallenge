package com.android.telexchallenge.data.network

import com.google.gson.annotations.SerializedName

data class SubTopic(@SerializedName("id") val id: String,
                    @SerializedName("createdAt") val createdAt: String,
                    @SerializedName("name") val name: String,
                    @SerializedName("price") val price: String,
                    @SerializedName("color") val color: String,
                    @SerializedName("image") val image: String)