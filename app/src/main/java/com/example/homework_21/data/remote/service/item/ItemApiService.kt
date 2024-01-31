package com.example.homework_21.data.remote.service.item

import com.example.homework_21.data.remote.model.item.ItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ItemApiService {
    @GET("1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getItemsList(): Response<List<ItemDto>>
}