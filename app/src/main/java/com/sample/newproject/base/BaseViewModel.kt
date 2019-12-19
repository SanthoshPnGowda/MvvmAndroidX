package com.sample.newproject.base

import androidx.lifecycle.ViewModel
import com.sample.newproject.injection.component.DaggerViewModelInjector
import com.sample.newproject.injection.component.ViewModelInjector
import com.sample.newproject.injection.module.NetworkModule
import com.sample.newproject.ui.home.HomeViewModel

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
            is HomeViewModel ->injector.inject(this)
        }
    }
}