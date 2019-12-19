package com.indiqube.audit360.injection.component

import com.indiqube.audit360.injection.module.NetworkModule
import com.indiqube.audit360.ui.home.HomeViewModel
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