package com.indiqube.audit360.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indiqube.audit360.model.Post
import com.indiqube.audit360.model.PostDao


@Database(entities = arrayOf(Post::class),version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun postDao():PostDao
}