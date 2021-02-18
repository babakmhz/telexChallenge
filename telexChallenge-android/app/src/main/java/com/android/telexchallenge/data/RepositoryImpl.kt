package com.android.telexchallenge.data

import javax.inject.Inject

class RepositoryImpl @Inject constructor(
        private val apiHelper: ApiHelper
) : RepositoryHelper {

}