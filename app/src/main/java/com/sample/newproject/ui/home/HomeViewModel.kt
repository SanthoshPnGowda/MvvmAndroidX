package com.sample.newproject.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sample.newproject.base.BaseViewModel
import com.sample.newproject.model.Post
import com.sample.newproject.model.PostDao
import com.sample.newproject.network.ApiError
import com.sample.newproject.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel(private val postDao: PostDao) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter = PostListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPost() }

    private lateinit var subscription: Disposable


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadPost() {
        subscription = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    onRetrievePostListStart()
                }
                .doOnTerminate {
                    onRetrievePostFinish()
                }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { e -> onRetrievePostListError(ApiError(e)) }

                )

    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(E: ApiError?) {
        loadingVisibility.value = View.GONE
        Log.d("ERROR", E?.message)
        errorMessage.value = E?.message
    }
}