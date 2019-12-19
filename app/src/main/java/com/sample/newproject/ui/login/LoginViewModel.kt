package com.sample.newproject.ui.login

import androidx.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import com.sample.newproject.R
import com.sample.newproject.base.BaseViewModel
import com.sample.newproject.model.Post
import com.sample.newproject.network.ApiError
import com.sample.newproject.network.PostApi
import io.reactivex.disposables.Disposable

import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val login: MutableLiveData<Login> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val visitor: MutableLiveData<Boolean> = MutableLiveData()

    val errorClickListener = View.OnClickListener { }

    val isBack: MutableLiveData<Boolean> = MutableLiveData()
    val clickCount: MutableLiveData<Int> = MutableLiveData()

    val focuschange = View.OnFocusChangeListener { view: View?, b: Boolean ->
        if (!b) {
            if (view!!.id == R.id.email)
                email.value = (view as EditText).text.toString()
            else password.value = (view as EditText).text.toString()
        }


    }

    private lateinit var subscription: Disposable

    init {
        // loadPost()
        visitor.value = false
        isBack.value = false


    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized)
            subscription.dispose()
    }


    /*   fun login(email: String, password: String) {
           subscription = postApi.login(LoginRequest(email, password))
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .doOnSubscribe {
                       onRetrievePostListStart()
                   }
                   .doOnTerminate {
                       onRetrievePostFinish()
                   }
                   .subscribe(
                           { result -> onRetrieveLoginSuccess(result) },
                           { e -> onRetrievePostListError(ApiError(e)) }

                   )

       }*/


    private fun onRetrievePostListStart() {
        loadingVisibility.value = true
        // errorMessage.value = null
    }

    private fun onRetrievePostFinish() {
        loadingVisibility.value = false
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        // postListAdapter.updatePostList(postList)
        loadingVisibility.value = false
    }

    private fun onRetrieveLoginSuccess(login: Login) {
        Log.d("ERROR", Gson().toJson(login))
        this.login.value = login
        loadingVisibility.value = false
    }

    private fun onRetrievePostListError(E: ApiError?) {
        loadingVisibility.value = false
        Log.d("ERROR", E?.message)
        errorMessage.value = E?.message
    }


    /*  fun onClickLogin(v: View) {
          var intent = Intent((v.getParentActivity() as Context), Home::class.java)
          intent.putExtra("position", '1')
          v.getParentActivity()?.startActivity(intent)
      }*/


}