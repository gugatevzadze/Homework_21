package com.example.homework_21.data.local.datasource.items

import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.local.dao.item.ItemDao
import com.example.homework_21.data.local.mapper.item.toData
import com.example.homework_21.data.local.mapper.item.toDomain
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.datasource.local.LocalItemsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalItemsDataSourceImpl @Inject constructor(
    private val itemDao: ItemDao,
) : LocalItemsDataSource {
    override suspend fun getItemsLocally(): Flow<Resource<List<Item>>> = withContext(Dispatchers.IO) {
        itemDao.getItemsLocally().map { itemList ->
            if (itemList.isEmpty()) {
                Resource.Error("Items not found, swipe to refresh")
            } else {
                Resource.Success(itemList.map { it.toDomain() })
            }
        }
    }
    override suspend fun insertItemsLocally(items: List<Item>) {
        withContext(Dispatchers.IO) {
            itemDao.insertItemsLocally(items.map { it.toData() })
        }
    }
}