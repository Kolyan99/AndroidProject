package com.example.androidproject.domain.items

import android.content.ClipDescription
import com.example.androidproject.domain.model.FavoritesModel
import com.example.androidproject.domain.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getData(): Completable

    fun showData(): Observable<List<ItemsModel>>

   suspend fun deleteItemByDescription(description: String)

   suspend fun findItemByDescription(searchText: String): ItemsModel

   suspend fun favClicked(itemsModel: ItemsModel,  isFavorite: Boolean)

   suspend fun getFavorites(): List<FavoritesModel>
}