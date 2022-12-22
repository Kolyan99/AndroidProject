package com.example.androidproject.presentation.view.view.auth.home

import com.example.androidproject.domain.auth.AuthInteractor
import com.example.androidproject.presentation.view.view.MainActivity
import com.example.androidproject.presentation.view.view.MainView
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    private lateinit var mainView: MainView

    fun setView(mainActivity: MainActivity){
        mainView = mainActivity
    }



    fun checkUserExists(){
        val doesUserExists = authInteractor.checkUserExsist()
        mainView.userExistsResalt(doesUserExists)
    }
}