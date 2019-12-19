package com.indiqube.audit360.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post (
    val userid: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)