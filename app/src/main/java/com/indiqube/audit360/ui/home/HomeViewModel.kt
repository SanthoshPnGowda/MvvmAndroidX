package com.indiqube.audit360.ui.home

import androidx.lifecycle.MutableLiveData
import android.view.View
import com.indiqube.audit360.base.BaseViewModel
import com.indiqube.audit360.model.Post
import com.indiqube.audit360.model.PostDao
import com.indiqube.audit360.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeViewModel(private val postDao: PostDao) : BaseViewModel() {
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