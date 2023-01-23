package com.example.androidproject.data.dataBase.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidproject.data.dataBase.ItemsEntity

@Database(entities = [ItemsEntity::class], version = 1, exportSchema = false)
abstract class ItemsDatabase: RoomDatabase() {

    abstract fun getItemsDAO(): ItemsDao

    companion object{
        private const val DATABASE_NAME = "tms_db"
        private var DATABASE_INSTANCE: ItemsDatabase? = null

        fun getItemsDatabaseInstance(context: Context): ItemsDatabase{
            return DATABASE_INSTANCE?: Room
                .databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                .also {DATABASE_INSTANCE = it  }
        }
    }
}