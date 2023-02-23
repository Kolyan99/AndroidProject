package com.example.androidproject

import androidx.room.Dao
import com.example.androidproject.data.dataBase.Dao.ItemsDao
import com.example.androidproject.data.dataBase.FavoritesEntity
import com.example.androidproject.data.dataBase.ItemsEntity
import com.example.androidproject.data.items.ItemsRepositoryIml
import com.example.androidproject.data.model.ItemsResponse
import com.example.androidproject.data.service.ApiService
import com.example.androidproject.data.service.ApiServiceSecond
import com.example.androidproject.domain.auth.AuthRepository
import com.example.androidproject.domain.items.ItemsRepository
import com.example.androidproject.domain.model.ItemsModel
import com.google.android.gms.common.api.Response
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class ExampleUnitTest {

    lateinit var itemsRepository: ItemsRepository

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var apiServiceSecond: ApiServiceSecond

    @Mock
    lateinit var itemsDao: ItemsDao

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        itemsRepository = ItemsRepositoryIml(apiService, apiServiceSecond, itemsDao)
    }

    @Test
    fun `getData request succecful`() {

        val response = retrofit2.Response.success(ItemsResponse(listOf()))

        createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenReturn(response)
            itemsRepository.getData()
            verify (apiService, only()).getData()
        }
    }

    @Test
    fun `showData gave from database success`(){
        val itemsEntity = listOf(ItemsEntity(0, ",", ""))
        runBlocking {
            `when`(itemsDao.getItemsEntities()).thenReturn(itemsEntity)
            itemsRepository.showData()

            verify(itemsDao, times(1)).getItemsEntities()
        }
    }

    @Test(expected = Exception::class)
    fun `showData gave from database error`(){
        val itemsEntity = ItemsEntity(0, ", ", "")
        runBlocking {
            `when`(itemsDao.getItemsEntities()).thenThrow(Exception())
            itemsRepository.showData()

            verify(itemsDao, times(1)).getItemsEntities()
        }
    }

    @Test
    fun `deleteItemByDescription succes`(){
        runBlocking {
            doNothing().`when`(itemsDao)
                .deleteItemEntityByDescription("descr")
            itemsRepository.deleteItemByDescription("descr")

            verify(itemsDao, times(1))
                .deleteItemEntityByDescription("descr")
        }
    }

    @Test
    fun `getFavorites succes`(){
        val favEntity = listOf<FavoritesEntity>()
        runBlocking{
            `when`(itemsDao.getFavoritesEntities()).thenReturn(listOf())
            itemsRepository.getFavorites()
            verify(itemsDao, times(1)).getFavoritesEntities()
            assertEquals(favEntity, itemsDao.getFavoritesEntities())
            assertNotSame(itemsDao.getItemsEntities(), itemsDao.getFavoritesEntities())
        }
    }

}