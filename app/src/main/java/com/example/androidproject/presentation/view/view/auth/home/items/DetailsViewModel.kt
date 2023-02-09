package com.example.androidproject.presentation.view.view.auth.home.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.R
import com.example.androidproject.domain.auth.AuthInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailsViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    fun logoutUser(){
        viewModelScope.launch {
            authInteractor.logoutUser()
            _nav.value = R.navigation.auth_graph
        }
    }
}