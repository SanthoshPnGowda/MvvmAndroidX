package com.sample.newproject.utils.extension

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {
    companion object {
        val DEVELOP_MODE = false
        private val USERNAME = "username"
        private val USEREMAIL = "useremail"
        private val USERTOKEN = "usertoken"
        private val USER_ID = "user_id"
    }


    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    // save device token
    var user_name = preferences.getString(USERNAME, "N/A")
        set(value) = preferences.edit().putString(USERNAME, value).apply()
    var user_id = preferences.getString(USER_ID, "N/A")
        set(value) = preferences.edit().putString(USER_ID, value).apply()

    var user_email = preferences.getString(USEREMAIL, "N/A")
        set(value) = preferences.edit().putString(USEREMAIL, value).apply()

    var user_token = preferences.getString(USERTOKEN, "N/A")
        set(value) = preferences.edit().putString(USERTOKEN, value).apply()
}