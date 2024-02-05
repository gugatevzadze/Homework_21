package com.example.homework_21.data.repository.items

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.item.ItemsRepository
import com.example.homework_21.domain.datasource.local.LocalItemsDataSource
import com.example.homework_21.domain.datasource.remote.RemoteItemsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val remoteItemsDataSource: RemoteItemsDataSource,
    private val localItemsDataSource: LocalItemsDataSource,
) : ItemsRepository {
    override suspend fun getRemoteItems(): Flow<Resource<List<Item>>> = remoteItemsDataSource.getItemsRemotely()

    override suspend fun getLocalItems(): Flow<Resource<List<Item>>> = localItemsDataSource.getItemsLocally()

    override suspend fun saveItemsLocally(items: List<Item>) = localItemsDataSource.insertItemsLocally(items)

    override suspend fun getItemsByCategory(category: String): Flow<Resource<List<Item>>> = localItemsDataSource.getItemsByCategory(category)
}