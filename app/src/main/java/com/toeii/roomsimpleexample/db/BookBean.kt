package com.toeii.roomsimpleexample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookBean(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "book_name") var bookName: String?,
    @ColumnInfo(name = "is_like") var isLike: Boolean?
)