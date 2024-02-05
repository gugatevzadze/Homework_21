package com.example.homework_21.domain.usecase

import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.util.NetworkConnectionChecker
import javax.inject.Inject

class GetItemsByCategoryUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository,
) {
    suspend fun invoke(category: String) = itemsRepository.getItemsByCategory(category)
}