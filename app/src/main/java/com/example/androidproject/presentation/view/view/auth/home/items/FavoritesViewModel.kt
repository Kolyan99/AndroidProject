package com.example.androidproject.presentation.view.view.auth.home.items

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.domain.model.FavoritesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
private val itemsInteractor: ItemsInteractor

): ViewModel() {

    private val _favorites = MutableLiveData<List<FavoritesModel>>()
    val favorites = _favorites

    fun getFavorites(){
        viewModelScope.launch {
            try {
                val favoritesItems = itemsInteractor.getFavorites()
                _favorites.value = favoritesItems
            }catch (e: Exception){
                Log.w("Fav error", e.toString())
            }
        }
    }
}