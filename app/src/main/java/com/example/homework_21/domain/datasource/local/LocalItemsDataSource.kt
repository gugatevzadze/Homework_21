package com.example.homework_21.domain.datasource.local

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import kotlinx.coroutines.flow.Flow

interface LocalItemsDataSource {
    suspend fun getItemsLocally(): Flow<Resource<List<Item>>>

    suspend fun insertItemsLocally(items: List<Item>)

    suspend fun getItemsByCategory(category: String): Flow<Resource<List<Item>>>
}