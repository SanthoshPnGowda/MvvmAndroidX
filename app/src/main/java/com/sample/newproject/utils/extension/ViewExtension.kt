package com.sample.newproject.utils.extension

import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.sample.newproject.R
import com.sample.newproject.network.CheckConnectivityInterceptor
import io.reactivex.Single
import java.util.regex.Pattern

fun View.getParentActivity():AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper){
        if(context is AppCompatActivity){
            return context
        }

        context = context.baseContext
    }
    return null
}

fun AppCompatActivity.hideStatusBar() {
    if (Build.VERSION.SDK_INT >= 16) {
        this.window.setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT)
        this.window.decorView.systemUiVisibility = 3328
    } else {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}

fun AppCompatActivity.showLoading(): Dialog {
    val progressDialog = Dialog(this)
    progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    progressDialog.setContentView(R.layout.loading_view)
    progressDialog.setCanceledOnTouchOutside(false)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    return progressDialog
    //return list
}

fun String.isEmailValid(): Boolean {

    return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(this.toString()).matches()
}


fun AppCompatActivity.isConnected(): Boolean {
    return CheckConnectivityInterceptor(this).isConnectedToInternet
}

fun AppCompatActivity.isConnectedToInternetAccess(): Single<Boolean> {
    return CheckConnectivityInterceptor(this).hasInternetConnection()
}

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}



