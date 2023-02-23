package com.example.androidproject.data.dataBase.Dao

import android.content.ClipDescription
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.androidproject.data.dataBase.FavoritesEntity
import com.example.androidproject.data.dataBase.ItemsEntity
import com.example.androidproject.domain.model.ItemsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT * From ItemsEntity")
    fun getItemsEntities(): List<ItemsEntity>

    @Query("SELECT(SELECT COUNT(*) FROM ItemsEntity) !=0")
    fun doesItemsEntityExist(): Boolean

    @Query("DELETE FROM ItemsEntity WHERE description =:description ")
    fun deleteItemEntityByDescription(description: String)

    @Query("SELECT * FROM ItemsEntity WHERE description =:searchText ")
    fun findItemEntityByDescription(searchText: String): ItemsEntity

    @Insert(onConflict = IGNORE) // ignore when conflict occurs (ignore items if same)
    fun insertFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM FavoritesEntity ")
    fun getFavoritesEntities(): List<FavoritesEntity>
}