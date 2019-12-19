package com.indiqube.audit360.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.viewpager.widget.PagerAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.indiqube.audit360.R
import com.indiqube.audit360.databinding.ActivityHomeBinding
import com.indiqube.audit360.injection.ViewModelFactory
import com.indiqube.audit360.utils.extension.PreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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