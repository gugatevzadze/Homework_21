package com.example.homework_21.domain.usecase

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.item.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<Item>>> {
        return itemsRepository.getItems()
    }
}