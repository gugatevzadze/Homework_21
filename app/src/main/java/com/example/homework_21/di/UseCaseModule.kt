package com.example.homework_21.di

import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.usecase.GetItemsByCategoryUseCase
import com.example.homework_21.domain.usecase.GetItemsUseCase
import com.example.homework_21.domain.util.NetworkConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetItemsUseCase(
        itemsRepository: ItemsRepository,
        networkConnectionChecker: NetworkConnectionChecker
    ): GetItemsUseCase {
        return GetItemsUseCase(itemsRepository = itemsRepository, networkConnectionChecker = networkConnectionChecker)
    }

    @Provides
    @Singleton
    fun provideGetItemsByCategoryUseCase(
        itemsRepository: ItemsRepository,
    ): GetItemsByCategoryUseCase {
        return GetItemsByCategoryUseCase(itemsRepository = itemsRepository)
    }
}