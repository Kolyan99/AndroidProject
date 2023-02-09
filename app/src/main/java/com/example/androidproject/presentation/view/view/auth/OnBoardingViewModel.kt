package com.example.androidproject.presentation.view.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.R


class OnBoardingViewModel : ViewModel() {

    private val _nav = MutableLiveData<NavToItems?>()
    val nav: LiveData<NavToItems?> = _nav

    val _onBoardingText = MutableLiveData<String>("default value")

    fun finishButtonClick() {
        _nav.value = NavToItems(
            R.id.action_onBoardingFragment_to_itemsFragment,
            R.id.onBoardingFragment
        )
    }

    fun finishPerformed() {
        _nav.value = null
    }
}


data class NavToItems(
    val destinationId: Int,
    val removeFragment: Int
)