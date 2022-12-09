package com.example.androidproject.presentation.View

import com.example.androidproject.R
import com.example.androidproject.domain.ItemsInteractor
import com.example.androidproject.presentation.adapter.model.ItemsModel

class ItemsPresentler(
    private val itemsView: ItemsView,
    private val itemsInteractor: ItemsInteractor
    ) {

    fun getData(){
       val listItems = itemsInteractor.getData()
        itemsView.dateReceived(listItems)


    }

    fun imageViewClicked(){
        itemsView.imageViewClicked(R.string.imageview_clicked)
    }

    fun elementSelected(name: String, date: String, imageView: Int){
        itemsView.gotoDetails(name, date,imageView)

    }
}