package com.example.tech.mvvmproj.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.Toast
import com.example.tech.mvvmproj.R
import com.example.tech.mvvmproj.base.BaseViewModel
import com.example.tech.mvvmproj.model.Post
import com.example.tech.mvvmproj.model.PostDao
import com.example.tech.mvvmproj.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter = PostListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPost() }

    private lateinit var subscription: Disposable

    init {
        loadPost()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPost() {
        subscription = Observable.fromCallable { postDao.all }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    onRetrievePostListStart()
                }
                .doOnTerminate {
                    onRetrievePostFinish()
                }
                .subscribe(
                        {result -> onRetrievePostListSuccess(result)},
                        {e -> onRetrievePostListError(e.message) }

                )

    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun  onRetrievePostFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(E:String?){
        errorMessage.value = E
    }
}