package com.example.androidproject.di

import com.example.androidproject.domain.auth.AuthInteractor
import com.example.androidproject.domain.auth.AuthRepository
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.domain.items.ItemsRepository
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun provideItemsInteractor(
        itemsRepository: ItemsRepository
    ): ItemsInteractor {
        return ItemsInteractor(itemsRepository)
    }


    @Provides
    fun provideAuthInteractor(
        authRepository: AuthRepository
    ): AuthInteractor {
        return AuthInteractor(authRepository)
    }
}