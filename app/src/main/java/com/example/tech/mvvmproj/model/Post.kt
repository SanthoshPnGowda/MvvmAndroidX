package com.example.tech.mvvmproj.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Post (
    val userid: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)