package com.example.androidproject.presentation.view.view.auth.home

import android.widget.ImageView
import com.example.androidproject.domain.auth.AuthInteractor
import com.example.androidproject.presentation.view.view.DetailsView
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    private lateinit var detailsView: DetailsView

    fun setView(detailsFragment: DetailsFragment){
        detailsView = detailsFragment
    }

    fun getArguments(name: String?, date: String?, imageView: Int){
        detailsView.displayItemData(
            when(name.isNullOrEmpty()){
                true -> "NO DATA"
                false -> name },
            when(date.isNullOrEmpty()){
                true -> "NO DATA"
                false -> date },
            imageView
        )
    }

    fun logoutUser(){
        authInteractor.logoutUser()
        detailsView.userLoggedOut()
    }
}