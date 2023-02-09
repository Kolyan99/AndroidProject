package com.example.androidproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.di.factory.ViewModelFactory
import com.example.androidproject.di.factory.ViewModelKey
import com.example.androidproject.presentation.view.view.MainViewModel
import com.example.androidproject.presentation.view.view.auth.LoginViewModel
import com.example.androidproject.presentation.view.view.auth.OnBoardingViewModel
import com.example.androidproject.presentation.view.view.auth.home.HomeViewModel
import com.example.androidproject.presentation.view.view.auth.home.items.DetailsViewModel
import com.example.androidproject.presentation.view.view.auth.home.items.FavoritesViewModel
import com.example.androidproject.presentation.view.view.auth.home.items.ItemsViewModel
import com.example.androidproject.presentation.view.view.auth.home.items.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
   abstract fun bindViewModelFactory(ViewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel(viewModel: ItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}