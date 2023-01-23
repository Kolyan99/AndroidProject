package com.example.androidproject.data.items

import android.util.Log
import com.example.androidproject.data.dataBase.Dao.ItemsDao
import com.example.androidproject.data.dataBase.ItemsEntity
import com.example.androidproject.data.service.ApiService
import com.example.androidproject.data.service.ApiServiceSecond
import com.example.androidproject.domain.items.ItemsRepository
import com.example.androidproject.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

class ItemsRepositoryIml @Inject constructor(
   @Named("FIRST") private val apiService: ApiService,
   @Named("SECOND") private val apiServiceSecond: ApiServiceSecond,
   private val itemsDao: ItemsDao
): ItemsRepository {


    override suspend fun getData() {
        return withContext(Dispatchers.IO) {

            if (!itemsDao.doesItemsEntityExist()) {
                Log.w("getData", "data not exists")
                val response = apiService.getData()

                response.body()?.sampleList?.let {
                    it.map {
                        val itemsEntity = ItemsEntity(Random.nextInt(), it.description, it.imageUrl)
                        itemsDao.insertItemsEntity(itemsEntity)

                    }
                }
            }
        }
    }

    override suspend fun showData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDao.getItemsEntities()
            itemsEntity.map {
                ItemsModel(it.description, it.imageUrl)
            }
        }
    }

    override suspend fun deleteItemByDescription(description: String) {
        withContext(Dispatchers.IO) {
            itemsDao.deleteItemEntityByDescription(description)
        }
    }


//    override suspend fun findItemByDescription(searchText: String): ItemsModel {
//        return withContext(Dispatchers.IO){
//            val itemsEntity = itemsDao.findItemEntityByDescription(searchText)
//        }
//    }
//}
}