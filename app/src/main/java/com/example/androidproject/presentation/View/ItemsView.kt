package com.example.androidproject.presentation.View

import com.example.androidproject.presentation.adapter.model.ItemsModel

interface ItemsView {

    fun dateReceived(list: List<ItemsModel>)

    fun imageViewClicked(msg: Int)

    fun gotoDetails(name: String, date: String, imageView: Int)
}