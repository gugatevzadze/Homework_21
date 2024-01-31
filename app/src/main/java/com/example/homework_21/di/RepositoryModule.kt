package com.example.homework_21.di


import com.example.homework_21.data.local.dao.item.ItemDao
import com.example.homework_21.data.local.repository.items.LocalItemsRepositoryImpl
import com.example.homework_21.data.common.ResponseHandler
import com.example.homework_21.data.remote.repository.item.RemoteItemsRepositoryImpl
import com.example.homework_21.data.remote.service.item.ItemApiService
import com.example.homework_21.data.repository.items.ItemsRepositoryImpl
import com.example.homework_21.data.util.NetworkConnectionChecker

import com.example.homework_21.domain.repository.common.ItemsRepository
import com.example.homework_21.domain.repository.local.LocalItemsRepository

import com.example.homework_21.domain.repository.remote.RemoteItemsRepository
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
    fun provideRemoteItemsRepository(
        apiService: ItemApiService,
        responseHandler: ResponseHandler
    ): RemoteItemsRepository {
        return RemoteItemsRepositoryImpl(
            apiService = apiService,
            responseHandler = responseHandler
        )
    }

    @Provides
    @Singleton
    fun provideLocalItemsRepository(
        itemDao: ItemDao,
    ): LocalItemsRepository {
        return LocalItemsRepositoryImpl(
            itemDao = itemDao
        )
    }

    @Provides
    @Singleton
    fun provideItemRepository(
        remoteItemsRepository: RemoteItemsRepository,
        localItemsRepository: LocalItemsRepository,
        networkConnectionChecker: NetworkConnectionChecker
    ): ItemsRepository {
        return ItemsRepositoryImpl(
            remoteItemsRepository = remoteItemsRepository,
            localItemsRepository = localItemsRepository,
            networkConnectionChecker = networkConnectionChecker
        )
    }
}