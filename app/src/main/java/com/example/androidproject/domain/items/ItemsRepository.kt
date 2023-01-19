package com.example.androidproject.domain.items

import com.example.androidproject.domain.model.ItemsModel

interface ItemsRepository {

   suspend fun getData(): List<ItemsModel>
}