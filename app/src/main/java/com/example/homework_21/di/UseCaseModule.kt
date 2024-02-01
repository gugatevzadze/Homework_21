package com.example.homework_21.di

import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.usecase.GetItemsUseCase
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
        itemsRepository: ItemsRepository
    ): GetItemsUseCase {
        return GetItemsUseCase(itemsRepository = itemsRepository)
    }
}