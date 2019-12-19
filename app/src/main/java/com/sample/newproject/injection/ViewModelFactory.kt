package com.sample.newproject.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import com.sample.newproject.model.database.AppDatabase
import com.sample.newproject.ui.home.HomeViewModel


class ViewModelFactory(private val activity : AppCompatActivity): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java,"mvposts").build()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(db.postDao()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}