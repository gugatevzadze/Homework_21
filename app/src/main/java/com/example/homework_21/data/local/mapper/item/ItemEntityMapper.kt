package com.example.homework_21.data.local.mapper.item

import com.example.homework_21.data.local.model.item.ItemEntity
import com.example.homework_21.domain.model.item.Item

fun ItemEntity.toDomain(): Item {
    return Item(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
    )
}

fun Item.toData(): ItemEntity {
    return ItemEntity(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
    )
}