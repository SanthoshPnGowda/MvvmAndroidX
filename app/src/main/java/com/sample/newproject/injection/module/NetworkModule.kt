package com.sample.newproject.injection.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sample.newproject.BuildConfig
import com.sample.newproject.network.PostApi
import com.sample.newproject.utils.getBaseURL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    // provide the api implementation
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            httpClient.addNetworkInterceptor(StethoInterceptor())
        httpClient.addInterceptor(logging)
        return httpClient.build()

    }

    //provide retrofit object
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(getOkHttpClient())
                .build()
    }
}