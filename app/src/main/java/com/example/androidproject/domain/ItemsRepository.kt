package com.example.androidproject.domain

import com.example.androidproject.presentation.adapter.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>


}