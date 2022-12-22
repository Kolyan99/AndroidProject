package com.example.androidproject.presentation.view.view

import com.example.androidproject.R
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.presentation.view.view.auth.home.ItemsFragment
import javax.inject.Inject

class ItemsPresenter @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) {

    private lateinit var itemsView: ItemsView

    fun setView(itemsFragment: ItemsFragment){
        itemsView = itemsFragment
    }

    fun getItems(){
        val items = itemsInteractor.getData()
        itemsView.itemsReceived(items)
    }

    fun imageViewClicked(){
        itemsView.imageViewClicked(R.string.imageview_clicked)

    }

    fun itemClicked(name: String, date: String, imageView: Int){
        itemsView.itemClicked(NavigateWithBundel(name,date,imageView))
    }

}


data class NavigateWithBundel(
    val image: String,
    val name: String,
    val data: Int
)