package com.sample.newproject.network

import com.google.gson.JsonParser
import retrofit2.HttpException
import java.net.ConnectException

class ApiError constructor(error: Throwable) {
    var message = "An error occurred"
    var code = 0

    init {
        when (error) {
            is HttpException -> {
                val errorJsonString = error.response()?.errorBody()?.string()
                this.code = error.code()
                try {
                    this.message = JsonParser().parse(errorJsonString).asJsonObject["message"].asString
                } catch (e: Exception) {
                    this.message = "Couldn't get Api response"
                }
            }
            is ConnectException -> this.message = "No Internet Connection"
            else -> {
                this.message = error.message ?: this.message
                this.message = if (this.message.contains("Unable to resolve host")) "No Internet Connection " else this.message
            }
        }
    }
}