package com.example.androidproject.data.items

import android.util.Log
import com.example.androidproject.data.dataBase.Dao.ItemsDao
import com.example.androidproject.data.dataBase.FavoritesEntity
import com.example.androidproject.data.dataBase.ItemsEntity
import com.example.androidproject.data.service.ApiService
import com.example.androidproject.data.service.ApiServiceSecond
import com.example.androidproject.domain.items.ItemsRepository
import com.example.androidproject.domain.model.FavoritesModel
import com.example.androidproject.domain.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

class ItemsRepositoryIml @Inject constructor(
    @Named("FIRST") private val apiService: ApiService,
    @Named("SECOND") private val apiServiceSecond: ApiServiceSecond,
    private val itemsDao: ItemsDao
) : ItemsRepository {

    private val compositeDisposable = CompositeDisposable()

    override fun getData(): Completable {
           return itemsDao.doesItemsEntityExist()
                .subscribeOn(Schedulers.io())
                .doAfterNext {
                    if (!it) {
                        val response = apiService.getData()
                        val getData = response.subscribeOn(Schedulers.io())
                            .doAfterSuccess {
                                it.sampleList.map {
                                    val itemsEntity =
                                        ItemsEntity(Random.nextInt(), it.description, it.imageUrl)
                                    itemsDao.insertItemsEntity(itemsEntity)
                                }
                            }
                            .doOnError {
                                Log.w("error", "when making request")
                            }
                            .ignoreElement()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                        compositeDisposable.add(getData)
                    }
                }
               .ignoreElements()
               .observeOn(AndroidSchedulers.mainThread())
        }

    override fun showData(): Observable<List<ItemsModel>> {
        val itemsEntity = itemsDao.getItemsEntities()
        return itemsEntity.subscribeOn(Schedulers.io())
            .map {
                it.map {
                    ItemsModel(it.id, it.description, it.imageUrl, it.isFavorite ?: false)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }


    override suspend fun deleteItemByDescription(description: String) {
        withContext(Dispatchers.IO) {
            itemsDao.deleteItemEntityByDescription(description)
        }
    }


    override suspend fun findItemByDescription(searchText: String): ItemsModel {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDao.findItemEntityByDescription(searchText)
            ItemsModel(
                itemsEntity.id,
                itemsEntity.description,
                itemsEntity.imageUrl,
                itemsEntity.isFavorite ?: false
            )
        }
    }

    override suspend fun favClicked(itemsModel: ItemsModel, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {
            itemsDao.addToFavorite(
                itemsModel.description,
                isFavorite
            )

            itemsDao.insertFavoritesEntity(
                FavoritesEntity(
                    itemsModel.id,
                    itemsModel.description,
                    itemsModel.image
                )
            )
        }
    }

    override suspend fun getFavorites(): List<FavoritesModel> {
        return withContext(Dispatchers.IO) {
            val favoritesEntity = itemsDao.getFavoritesEntities()
            favoritesEntity.map {
                FavoritesModel(it.description, it.imageUrl)
            }
        }
    }
}
