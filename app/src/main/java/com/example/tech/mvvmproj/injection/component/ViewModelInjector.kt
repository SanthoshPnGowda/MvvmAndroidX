package com.example.tech.mvvmproj.injection.component

import com.example.tech.mvvmproj.injection.module.NetworkModule
import com.example.tech.mvvmproj.ui.post.PostListViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    // inject dependancy into the specified PostListView Model

    fun inject (postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder{
        fun build():ViewModelInjector

        fun networkModule(networkModule:NetworkModule) :  Builder
    }
}