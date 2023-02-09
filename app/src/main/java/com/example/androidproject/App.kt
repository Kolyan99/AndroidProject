package com.example.androidproject

import android.app.Application
import com.example.androidproject.di.AppModule
import com.example.androidproject.di.component.AppComponent
import com.example.androidproject.di.component.DaggerAppComponent


class App: Application() {

    lateinit var appComponent: AppComponent

    fun provideAppComponent(): AppComponent{
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        return appComponent
    }
}