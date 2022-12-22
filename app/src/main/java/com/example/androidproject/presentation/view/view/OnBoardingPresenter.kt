package com.example.androidproject.presentation.view.view

import com.example.androidproject.presentation.view.view.auth.OnBoardingFragment
import javax.inject.Inject

class OnBoardingPresenter @Inject constructor() {

    private lateinit var onBoardingView: OnBoardingView

    fun setView(onBoardingFragment: OnBoardingFragment){
        onBoardingView = onBoardingFragment
    }

    fun goToItemsFragment(){
        onBoardingView.goToItemsFragment()
    }
}