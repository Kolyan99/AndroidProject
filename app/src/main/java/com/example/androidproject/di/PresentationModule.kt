package com.example.androidproject.di

import com.example.androidproject.domain.auth.AuthInteractor
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.presentation.view.view.ItemsPresenter
import com.example.androidproject.presentation.view.view.LoginPresenter
import com.example.androidproject.presentation.view.view.OnBoardingPresenter
import com.example.androidproject.presentation.view.view.auth.home.DetailsPresenter
import com.example.androidproject.presentation.view.view.auth.home.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun provideMainPresenter(authInteractor: AuthInteractor): MainPresenter{
        return MainPresenter(authInteractor)
    }


    @Provides
    fun provideLoginPresenter(authInteractor: AuthInteractor): LoginPresenter{
        return LoginPresenter(authInteractor)
    }


    @Provides
    fun provideOnBoardingPresenter(): OnBoardingPresenter{
        return OnBoardingPresenter()
    }

    @Provides
    fun provideItemsPresenter(itemsInteractor: ItemsInteractor): ItemsPresenter{
        return ItemsPresenter(itemsInteractor)
    }

    @Provides
    fun provideDetailsPresenter(authInteractor: AuthInteractor): DetailsPresenter{
        return DetailsPresenter(authInteractor)
    }


}