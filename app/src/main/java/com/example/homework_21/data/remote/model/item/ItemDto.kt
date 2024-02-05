package com.example.homework_21.data.remote.model.item


data class ItemDto (
    val id:Int,
    val cover:String,
    val price:String,
    val title:String,
    val favorite:Boolean,
    val category: String
)