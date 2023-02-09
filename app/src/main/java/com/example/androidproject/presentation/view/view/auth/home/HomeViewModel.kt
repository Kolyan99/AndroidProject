package com.example.androidproject.presentation.view.view.auth.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.R
import com.example.androidproject.domain.auth.AuthInteractor
import com.example.androidproject.domain.model.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userCreds = MutableLiveData<UserModel>()
    val userCreds: LiveData<UserModel> = _userCreds

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav


    fun showUserData(){
        viewModelScope.launch {
            _userCreds.value = authInteractor.getUserCreds()
        }
    }

    fun goToOnBoarding(){
        _nav.value = R.navigation.main_graph
    }
}

