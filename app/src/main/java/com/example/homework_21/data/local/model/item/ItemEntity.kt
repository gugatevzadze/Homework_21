package com.example.homework_21.data.local.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity (
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name="cover")
    val cover:String,
    @ColumnInfo(name="price")
    val price:String,
    @ColumnInfo(name="title")
    val title:String,
    @ColumnInfo(name="favorite")
    val favorite:Boolean,
)
