package com.example.homework_21.domain.usecase

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.util.NetworkConnectionChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository,
    private val networkConnectionChecker: NetworkConnectionChecker
) {
    suspend operator fun invoke(): Flow<Resource<List<Item>>> = flow {
        networkConnectionChecker.networkStateFlow.collect { networkState ->
            if (networkState) {
                itemsRepository.getRemoteItems().collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            itemsRepository.saveItemsLocally(resource.data)
                            emit(resource)
                        }
                        is Resource.Error -> emit(resource)
                        is Resource.Loading -> emit(resource)
                    }
                }
            } else {
                itemsRepository.getLocalItems().collect { resource ->
                    emit(resource)
                }
            }
        }
    }
}