package com.example.homework_21.data.local.dao.item

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework_21.data.local.model.item.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM itemEntity")
    fun getItemsLocally(): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsLocally(items: List<ItemEntity>)

    @Query("SELECT * FROM itemEntity WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<ItemEntity>>
}