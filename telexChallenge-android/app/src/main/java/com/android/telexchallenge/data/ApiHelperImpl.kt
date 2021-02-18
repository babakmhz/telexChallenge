package com.android.telexchallenge.data

import com.android.telexchallenge.data.ApiHelper
import com.android.telexchallenge.data.ApiService
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
}