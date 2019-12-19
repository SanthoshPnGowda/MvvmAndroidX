package com.sample.newproject.injection.component

import com.sample.newproject.injection.module.NetworkModule
import com.sample.newproject.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    // inject dependancy into the specified PostListView Model

    fun inject (homeModel: HomeViewModel)

    @Component.Builder
    interface Builder{
        fun build():ViewModelInjector

        fun networkModule(networkModule:NetworkModule) :  Builder
    }
}