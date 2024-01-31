package com.example.homework_21.data.local.repository.items

import android.util.Log
import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.local.dao.item.ItemDao
import com.example.homework_21.data.local.mapper.item.toData
import com.example.homework_21.data.local.mapper.item.toDomain
import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.domain.repository.local.LocalItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalItemsRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao,
) : LocalItemsRepository {
    override suspend fun getItemsLocally(): Flow<Resource<List<Item>>> {
        return itemDao.getItemsLocally().map { itemList ->
            if (itemList.isEmpty()) {
                Resource.Error("Items not found")
            } else {
                Resource.Success(itemList.map { it.toDomain() })
            }
        }
    }
    override suspend fun insertItemsLocally(items: List<Item>) {
        itemDao.insertItemsLocally(items.map { it.toData() })
    }
}