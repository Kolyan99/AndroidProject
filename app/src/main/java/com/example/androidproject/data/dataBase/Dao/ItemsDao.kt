package com.example.androidproject.data.dataBase.Dao

import android.content.ClipDescription
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidproject.data.dataBase.ItemsEntity
import com.example.androidproject.domain.model.ItemsModel

@Dao
interface ItemsDao {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT * From ItemsEntity")
    fun getItemsEntities(): List<ItemsEntity>

    @Query("SELECT(SELECT COUNT(*) FROM ItemsEntity) !=0")
    fun doesItemsEntityExist():Boolean

    @Query("DELETE FROM ItemsEntity WHERE description =:description ")
    fun deleteItemEntityByDescription(description: String)
}