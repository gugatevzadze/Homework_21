package com.example.homework_21.presentation.mapper.item

import com.example.homework_21.domain.model.item.Item
import com.example.homework_21.presentation.model.item.ItemModel

fun Item.toPresentation(): ItemModel{
    return ItemModel(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )
}
