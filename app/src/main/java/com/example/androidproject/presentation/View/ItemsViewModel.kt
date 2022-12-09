package com.example.androidproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.presentation.adapter.model.ItemsModel

class ItemsViewModel: ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundel?>()
    val bundel: LiveData<NavigateWithBundel?> = _bundle

    fun getData(){
        val listItems = listOf<ItemsModel>(
            ItemsModel(R.drawable.fruit,
                "Android",
                "26.02.2023"),
            ItemsModel(
                R.drawable.fruit,
                "C++",
                "26.02.2023"),
            ItemsModel(
                R.drawable.img,
                "C",
                "26.02.2024"),
            ItemsModel(
                R.drawable.img_1,
                "Java",
                "26.02.2026"),
            ItemsModel(
                R.drawable.fruit,
                "IOS",
                "26.04.2020"),
            ItemsModel(
                R.drawable.fruit,
                "PHP",
                "26.02.2023"),
            ItemsModel(
                R.drawable.fruit,
                "JS",
                "22.02.2021")
        )
        _items.value = listItems
    }

    fun imageViewClicked(){
        _msg.value = R.string.imageview_clicked

    }

    fun elementClicked(name: String, date: String, imageView: Int){
        _bundle.value = NavigateWithBundel(
            name = name,
            data= date,
            image = imageView
        )
    }

    fun userNavigated(){
        _bundle.value = null
    }
}

data class NavigateWithBundel(
    val image: Int,
    val name: String,
    val data: String
)