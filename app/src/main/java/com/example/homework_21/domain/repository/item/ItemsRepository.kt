package com.example.homework_21.domain.repository.item

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    suspend fun getItems(): Flow<Resource<List<Item>>>
}