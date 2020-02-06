package com.sample.newproject.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.sample.newproject.R
import com.sample.newproject.databinding.ActivityHomeBinding
import com.sample.newproject.injection.ViewModelFactory
import com.sample.newproject.utils.extension.PreferencesHelper
import com.sample.newproject.utils.extension.isConnected
import com.sample.newproject.utils.extension.isConnectedToInternetAccess
import com.sample.newproject.utils.extension.toast

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var errorSnackbar: Snackbar? = null
    private var preferencesHelper: PreferencesHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        if (isConnected()) {
            isConnectedToInternetAccess().subscribe { hasInternet ->
                if (hasInternet)
                    viewModel.loadPost()
                else
                    toast("No Internet Access,Please switch the network")
            }
        } else
            toast("No Internet Connection")
    }

    private fun showError(@SuppressLint("SupportAnnotationUsage") @StringRes errorMessage: String) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()

    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}