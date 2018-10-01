package com.example.tech.mvvmproj.base

import android.arch.lifecycle.ViewModel
import com.example.tech.mvvmproj.injection.component.DaggerViewModelInjector
import com.example.tech.mvvmproj.injection.component.ViewModelInjector
import com.example.tech.mvvmproj.injection.module.NetworkModule
import com.example.tech.mvvmproj.ui.post.PostListViewModel

abstract class BaseViewModel:ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()


    init {
        inject()
    }

    // injects the required depandancy
    private fun inject(){
        when(this){
            is PostListViewModel ->injector.inject(this)
        }
    }
}