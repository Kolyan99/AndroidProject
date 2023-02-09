package com.example.androidproject.di.component

import com.example.androidproject.di.*
import com.example.androidproject.di.factory.ScreenScope
import com.example.androidproject.presentation.view.view.MainActivity
import com.example.androidproject.presentation.view.view.auth.LoginFragment
import com.example.androidproject.presentation.view.view.auth.home.HomeFragment
import com.example.androidproject.presentation.view.view.auth.home.items.DetailsFragment
import com.example.androidproject.presentation.view.view.auth.home.items.FavoritesFragment
import com.example.androidproject.presentation.view.view.auth.home.items.ItemsFragment
import com.example.androidproject.presentation.view.view.auth.home.items.SearchFragment
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DataBaseModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)

@ScreenScope
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(itemsFragment: ItemsFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(activity: MainActivity)

}