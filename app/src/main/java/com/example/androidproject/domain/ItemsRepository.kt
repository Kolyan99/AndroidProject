package com.example.androidproject.domain

import com.example.androidproject.presentation.view.view.adapter.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>
}