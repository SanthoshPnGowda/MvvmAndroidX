package com.sample.newproject.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.sample.newproject.R
import com.sample.newproject.databinding.ActivityHomeBinding
import com.sample.newproject.injection.ViewModelFactory
import com.sample.newproject.utils.extension.PreferencesHelper

class Home:AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var errorSnackbar: Snackbar? = null
    private var preferencesHelper: PreferencesHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProviders.of(this,ViewModelFactory(this)).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage!=null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@SuppressLint("SupportAnnotationUsage") @StringRes errorMessage:String){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()

    }
    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}