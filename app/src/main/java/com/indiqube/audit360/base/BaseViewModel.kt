package com.indiqube.audit360.base

import androidx.lifecycle.ViewModel
import com.indiqube.audit360.injection.component.DaggerViewModelInjector
import com.indiqube.audit360.injection.component.ViewModelInjector
import com.indiqube.audit360.injection.module.NetworkModule
import com.indiqube.audit360.ui.home.HomeViewModel

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