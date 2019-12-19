package com.sample.newproject.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.newproject.model.Post
import com.sample.newproject.model.PostDao


@Database(entities = arrayOf(Post::class),version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun postDao():PostDao
}