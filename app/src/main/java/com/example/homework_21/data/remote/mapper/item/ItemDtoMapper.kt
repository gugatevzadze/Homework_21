package com.example.homework_21.data.remote.mapper.item

import com.example.homework_21.data.remote.model.item.ItemDto
import com.example.homework_21.domain.model.item.Item

fun ItemDto.toDomain(): Item {
    return Item(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category
    )
}