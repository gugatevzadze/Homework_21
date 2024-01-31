package com.example.homework_21.data.remote.repository.item

import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.common.ResponseHandler
import com.example.homework_21.data.remote.mapper.base.mapToDomain
import com.example.homework_21.data.remote.mapper.item.toDomain
import com.example.homework_21.data.remote.service.item.ItemApiService
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.remote.RemoteItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteItemsRepositoryImpl @Inject constructor(
    private val apiService: ItemApiService,
    private val responseHandler: ResponseHandler
) : RemoteItemsRepository {
    override suspend fun getItemsRemotely(): Flow<Resource<List<Item>>> {
        return responseHandler.safeApiCall {
            apiService.getItemsList()
        }.mapToDomain { itemsDto ->
            itemsDto.map { it.toDomain() }
        }
    }
}