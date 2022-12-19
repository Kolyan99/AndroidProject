package com.example.androidproject.di

import com.example.androidproject.domain.ItemsInteractor
import com.example.androidproject.domain.ItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideItemsInteractor(
        itemsRepository: ItemsRepository
    ): ItemsInteractor{
        return ItemsInteractor(itemsRepository)
    }
}