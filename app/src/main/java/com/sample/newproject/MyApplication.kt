package com.sample.newproject

import android.app.Application
import com.facebook.stetho.Stetho


class MyApplication : Application() {

    companion object;

    override fun onCreate() {
        super.onCreate()
//        var mServiceComponent = ComponentName(this, MyJobService::class.java)
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)


    }


}