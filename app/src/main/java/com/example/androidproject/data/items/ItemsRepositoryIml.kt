package com.example.androidproject.data.items

import android.util.Log
import com.example.androidproject.data.service.ApiService
import com.example.androidproject.data.service.ApiServiceSecond
import com.example.androidproject.domain.items.ItemsRepository
import com.example.androidproject.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ItemsRepositoryIml @Inject constructor(
   @Named("FIRST") private val apiService: ApiService,
   @Named("SECOND") private val apiServiceSecond: ApiServiceSecond
): ItemsRepository {


    override suspend fun getData(): List<ItemsModel> {
        return withContext(Dispatchers.IO){

            val responseSecond = apiServiceSecond.getPhotoByID(3)
            Log.w("Second Response", responseSecond.body()?.title.toString())

            val responseSecondQuery = apiServiceSecond.getPhotoByTitle("officia delectus consequatur vero aut veniam explicabo molestias")
            Log.w("Second Response Query", responseSecondQuery.body()!!.get(0).toString())

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