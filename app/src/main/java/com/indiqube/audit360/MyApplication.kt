package com.indiqube.audit360

import android.app.Application
import com.facebook.stetho.Stetho
import com.indiqube.audit360.BuildConfig


class MyApplication : Application() {

    companion object;

    override fun onCreate() {
        super.onCreate()
//        var mServiceComponent = ComponentName(this, MyJobService::class.java)
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)


    }


}