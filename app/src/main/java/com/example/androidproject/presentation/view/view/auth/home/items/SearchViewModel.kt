package com.example.androidproject.presentation.view.view.auth.home.items

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.domain.model.ItemsModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) : ViewModel() {

    private val _items = MutableLiveData<ItemsModel>()
    val item: LiveData<ItemsModel> = _items

    fun findItem(searchText: String) {
        viewModelScope.launch {
            try {
                val foundItems = itemsInteractor.findItem(searchText)
                _items.value = foundItems
            } catch (e: Exception) {
                Log.w("exception", e.toString())
            }
        }
    }
}
