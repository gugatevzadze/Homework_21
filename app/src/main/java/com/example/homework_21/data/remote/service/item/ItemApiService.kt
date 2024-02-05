package com.example.homework_21.data.remote.service.item

import com.example.homework_21.data.remote.model.item.ItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ItemApiService {
    @GET("df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getItemsList(): Response<List<ItemDto>>
}