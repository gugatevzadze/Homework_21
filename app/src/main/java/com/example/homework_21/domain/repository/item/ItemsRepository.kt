package com.example.homework_21.domain.repository.item

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    suspend fun getRemoteItems(): Flow<Resource<List<Item>>>
    suspend fun getLocalItems(): Flow<Resource<List<Item>>>
    suspend fun saveItemsLocally(items: List<Item>)

    suspend fun getItemsByCategory(category: String): Flow<Resource<List<Item>>>
}