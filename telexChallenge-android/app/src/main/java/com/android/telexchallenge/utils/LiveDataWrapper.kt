package com.android.telexchallenge.utils

class LiveDataWrapper<T>(val responseRESPONSESTATUS: RESPONSESTATUS, val response: T? = null, val error: Throwable? = null, message: String? = null) {

    enum class RESPONSESTATUS {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> loading(message: String?) = LiveDataWrapper<T>(RESPONSESTATUS.LOADING, message = message)
        fun <T> success(data: T) = LiveDataWrapper<T>(RESPONSESTATUS.SUCCESS, data)
        fun <T> error(err: Throwable, message: String?) = LiveDataWrapper<T>(RESPONSESTATUS.ERROR, null, err, message = message
        )
    }
}