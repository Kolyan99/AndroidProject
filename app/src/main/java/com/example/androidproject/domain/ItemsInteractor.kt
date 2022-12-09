package com.example.androidproject.domain

import com.example.androidproject.presentation.adapter.model.ItemsModel

class ItemsInteractor(private val itemsRepository: ItemsRepository) {

    fun getData(): List<ItemsModel> {
        itemsRepository.getData()
        return itemsRepository.getData()
    }
}