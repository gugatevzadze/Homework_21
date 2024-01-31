package com.example.homework_21.domain.repository.local

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import kotlinx.coroutines.flow.Flow

interface LocalItemsRepository {
    suspend fun getItemsLocally(): Flow<Resource<List<Item>>>

    suspend fun insertItemsLocally(items: List<Item>)
}