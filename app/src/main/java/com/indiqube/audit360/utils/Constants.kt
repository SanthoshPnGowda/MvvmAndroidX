package com.indiqube.audit360.utils

import com.indiqube.audit360.BuildConfig

const val BASE_URL_DEV: String = "https://jsonplaceholder.typicode.com"

const val BASE_URL_PROD: String = "https://jsonplaceholder.typicode.com"


enum class TICKET_TYPE(val `val`: Int, val `key`: String) {
    ALL(1, "ALL"), SAVED(2, "SAVED"),
    SUBITTED(3, "SUBMITTED")
}

/*Get Base Url based On Build Configuration*/
fun getBaseURL(): String {
    return if (BuildConfig.DEBUG) BASE_URL_DEV else BASE_URL_PROD
}