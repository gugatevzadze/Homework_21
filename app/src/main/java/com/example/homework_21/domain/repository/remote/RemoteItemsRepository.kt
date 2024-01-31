package com.example.homework_21.domain.repository.remote

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import kotlinx.coroutines.flow.Flow

interface RemoteItemsRepository {
    suspend fun getItemsRemotely(): Flow<Resource<List<Item>>>
}