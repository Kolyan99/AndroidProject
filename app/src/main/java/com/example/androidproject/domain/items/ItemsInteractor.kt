package com.example.androidproject.domain.items

import com.example.androidproject.domain.model.ItemsModel
import javax.inject.Inject

class ItemsInteractor @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    suspend fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}