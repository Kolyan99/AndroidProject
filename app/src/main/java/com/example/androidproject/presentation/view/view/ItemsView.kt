package com.example.androidproject.presentation.view.view

import com.example.androidproject.domain.model.ItemsModel

interface ItemsView {

    fun itemsReceived(itemsList: List<ItemsModel>)

    fun imageViewClicked(msg: Int)

    fun itemClicked(navigationData: NavigateWithBundel)
}