package com.example.androidproject.data.items

import com.example.androidproject.R
import com.example.androidproject.data.ApiService
import com.example.androidproject.domain.items.ItemsRepository
import com.example.androidproject.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemsRepositoryIml @Inject constructor(
    private val apiService: ApiService
): ItemsRepository {


    override suspend fun getData(): List<ItemsModel> {
        return withContext(Dispatchers.IO){
            val response = apiService.getData()
            response.body()?.sampleList?.let {
                it.map {
                    ItemsModel(it.description, it.imageUrl)
                }
            }?: kotlin.run {
                emptyList()
            }
        }
    }
}