package com.example.androidproject.data

import com.example.androidproject.R
import com.example.androidproject.domain.ItemsRepository
import com.example.androidproject.presentation.adapter.model.ItemsModel

class ItemsRepositoryImpl :ItemsRepository {

    override fun getData(): List<ItemsModel> {
        val listItems = listOf<ItemsModel>(
            ItemsModel(
                R.drawable.fruit,
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
        return listItems
    }

}