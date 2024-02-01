package com.example.homework_21.di

import com.example.homework_21.data.common.ResponseHandler
import com.example.homework_21.data.local.dao.item.ItemDao
import com.example.homework_21.data.local.datasource.items.LocalItemsDataSourceImpl
import com.example.homework_21.data.remote.datasource.item.RemoteItemsDataSourceImpl
import com.example.homework_21.data.remote.service.item.ItemApiService
import com.example.homework_21.domain.datasource.local.LocalItemsDataSource
import com.example.homework_21.domain.datasource.remote.RemoteItemsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteItemsDataSource(
        apiService: ItemApiService,
        responseHandler: ResponseHandler
    ): RemoteItemsDataSource {
        return RemoteItemsDataSourceImpl(
            apiService = apiService,
            responseHandler = responseHandler
        )
    }

    @Provides
    @Singleton
    fun provideLocalItemsDataSource(
        itemDao: ItemDao,
    ): LocalItemsDataSource {
        return LocalItemsDataSourceImpl(
            itemDao = itemDao
        )
    }
}