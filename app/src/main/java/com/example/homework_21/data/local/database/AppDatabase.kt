package com.example.homework_21.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_21.data.local.dao.item.ItemDao
import com.example.homework_21.data.local.model.item.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}