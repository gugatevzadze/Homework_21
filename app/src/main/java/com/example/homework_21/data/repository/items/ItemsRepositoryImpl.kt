package com.example.homework_21.data.repository.items

import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.util.NetworkConnectionChecker
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.datasource.local.LocalItemsDataSource
import com.example.homework_21.domain.datasource.remote.RemoteItemsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val remoteItemsDataSource: RemoteItemsDataSource,
    private val localItemsDataSource: LocalItemsDataSource,
    private val networkConnectionChecker: NetworkConnectionChecker
) : ItemsRepository {
    override suspend fun getItems(): Flow<Resource<List<Item>>> = flow {
        networkConnectionChecker.networkStateFlow.collect { networkState ->
            if (networkState) {
                remoteItemsDataSource.getItemsRemotely().collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            localItemsDataSource.insertItemsLocally(resource.data)
                        }
                        is Resource.Error -> emit(resource)
                        is Resource.Loading -> emit(resource)
                    }
                }
            }
            localItemsDataSource.getItemsLocally().collect { resource ->
                emit(resource)
            }
        }
    }
}