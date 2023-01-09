package com.example.androidproject.presentation.view.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userExists = MutableLiveData<Boolean>()
    val userExsits: LiveData<Boolean> = _userExists

    fun checkUserExists(){
        viewModelScope.launch {
            _userExists.value = authInteractor.checkUserExsist()
        }
    }
}