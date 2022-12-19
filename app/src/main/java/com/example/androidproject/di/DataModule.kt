package com.example.androidproject.di

import com.example.androidproject.data.ItemsRepositoryIml
import com.example.androidproject.domain.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {


    @Binds
    abstract fun bindItemsRepository(
        itemsRepositoryGet: ItemsRepositoryIml
    ): ItemsRepository
}