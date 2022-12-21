package com.example.androidproject.domain.items

import com.example.androidproject.domain.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>
}