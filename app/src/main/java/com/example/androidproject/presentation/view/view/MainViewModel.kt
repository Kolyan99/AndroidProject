package com.example.androidproject.presentation.view.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.R
import com.example.androidproject.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userExists = MutableLiveData<Int>()
    val userExsits: LiveData<Int> = _userExists

    fun checkUserExists(){
        viewModelScope.launch {
            val doesUserExsists = authInteractor.checkUserExsist()
            _userExists.value = when (doesUserExsists){
                true -> R.navigation.main_graph
                false -> R.navigation.auth_graph
            }
        }
    }
}