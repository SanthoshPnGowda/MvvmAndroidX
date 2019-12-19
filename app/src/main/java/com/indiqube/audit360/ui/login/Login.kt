package com.indiqube.audit360.ui.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.indiqube.audit360.R
import com.indiqube.audit360.databinding.ActivityLoginBinding
import com.indiqube.audit360.injection.ViewModelFactory
import com.indiqube.audit360.ui.home.Home
import com.indiqube.audit360.utils.extension.PreferencesHelper
import com.indiqube.audit360.utils.extension.hideStatusBar
import com.indiqube.audit360.utils.extension.isEmailValid
import com.indiqube.audit360.utils.extension.showLoading
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var loading: Dialog
    private var preferencesHelper: PreferencesHelper? = null
    var loginDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.hideStatusBar()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        loading = this.showLoading()

        email?.onFocusChangeListener = viewModel.focuschange
        password?.onFocusChangeListener = viewModel.focuschange
        preferencesHelper = PreferencesHelper(this)

        viewModel.login.observe(this, Observer { data ->

            var intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        })

        viewModel.loadingVisibility.observe(this, Observer { data ->
            if (data!!) loading.show() else loading.dismiss()
        })

        viewModel.errorMessage.observe(this, Observer { data ->
            if (data!!.contains("401"))
                Snackbar.make(binding.root, "Invalid Username or Password", Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(binding.root, data, Snackbar.LENGTH_SHORT).show()
        })

        signin.setOnClickListener {
            tlemail.error = if (!email.text.toString().isEmailValid()) "Invalid Email" else null
            tlpass.error = if (password.text.length < 6) "Invalid Password" else null
            if (tlemail.error == null && tlpass.error == null) {
                // viewModel.login(email.text.toString(), password.text.toString())
                startActivity(Intent(this, Home::class.java))
            }


        }
    }

}
