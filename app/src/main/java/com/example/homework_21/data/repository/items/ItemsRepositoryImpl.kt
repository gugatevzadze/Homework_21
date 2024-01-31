package com.example.homework_21.data.repository.items

import android.util.Log
import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.util.NetworkConnectionChecker
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.common.ItemsRepository
import com.example.homework_21.domain.repository.local.LocalItemsRepository
import com.example.homework_21.domain.repository.remote.RemoteItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val remoteItemsRepository: RemoteItemsRepository,
    private val localItemsRepository: LocalItemsRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) : ItemsRepository {
    override suspend fun getItems(): Flow<Resource<List<Item>>> = flow {
        networkConnectionChecker.networkStateFlow.collect { networkState ->
            if (networkState) {
                remoteItemsRepository.getItemsRemotely().collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            localItemsRepository.insertItemsLocally(resource.data)
                            emit(resource)
                        }
                        is Resource.Error -> emit(resource)
                        is Resource.Loading -> emit(resource)
                    }
                }
            } else {
                localItemsRepository.getItemsLocally().collect { resource ->
                    emit(resource)
                }
            }
        }
    }
}