package com.sample.newproject.utils

import com.sample.newproject.BuildConfig

const val BASE_URL_DEV: String = "https://jsonplaceholder.typicode.com"

const val BASE_URL_PROD: String = "https://jsonplaceholder.typicode.com"


enum class TICKET_TYPE(val `val`: Int, val `key`: String) {
    ALL(1, "ALL"), SAVED(2, "SAVED"),
    SUBMITTED(3, "SUBMITTED")
}

/*Get Base Url based On Build Configuration*/
fun getBaseURL(): String {
    return if (BuildConfig.DEBUG) BASE_URL_DEV else BASE_URL_PROD
}