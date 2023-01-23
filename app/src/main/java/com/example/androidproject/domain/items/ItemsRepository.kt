package com.example.androidproject.domain.items

import android.content.ClipDescription
import com.example.androidproject.domain.model.ItemsModel

interface ItemsRepository {

   suspend fun getData()

   suspend fun showData(): List<ItemsModel>

   suspend fun deleteItemByDescription(description: String)

   suspend fun findItemByDescription(searchText: String): ItemsModel
}