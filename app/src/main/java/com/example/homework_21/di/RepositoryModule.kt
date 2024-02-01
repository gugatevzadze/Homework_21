package com.example.homework_21.di


import com.example.homework_21.data.repository.items.ItemsRepositoryImpl
import com.example.homework_21.data.util.NetworkConnectionChecker

import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.datasource.local.LocalItemsDataSource

import com.example.homework_21.domain.datasource.remote.RemoteItemsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideItemRepository(
        remoteItemsDataSource: RemoteItemsDataSource,
        localItemsDataSource: LocalItemsDataSource,
        networkConnectionChecker: NetworkConnectionChecker
    ): ItemsRepository {
        return ItemsRepositoryImpl(
            remoteItemsDataSource = remoteItemsDataSource,
            localItemsDataSource = localItemsDataSource,
            networkConnectionChecker = networkConnectionChecker
        )
    }
}