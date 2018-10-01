package com.example.tech.mvvmproj.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.tech.mvvmproj.model.Post
import com.example.tech.mvvmproj.model.PostDao
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration




@Database(entities = arrayOf(Post::class),version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun postDao():PostDao
}