package com.example.androidproject.di

import android.content.Context
import com.example.androidproject.data.dataBase.Dao.ItemsDao
import com.example.androidproject.data.dataBase.Dao.ItemsDatabase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun provideItemsDao(itemsDatabase: ItemsDatabase): ItemsDao{
        return itemsDatabase.getItemsDAO()
    }

    @Provides
    fun itemsDatabase(context: Context): ItemsDatabase{
        return ItemsDatabase.getItemsDatabaseInstance(context)
    }
}