package com.example.androidproject.presentation.view.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.R
import com.example.androidproject.domain.ItemsInteractor
import com.example.androidproject.presentation.view.view.adapter.model.ItemsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val testParametr: TestParametr,
    private val itemsInteractor: ItemsInteractor
) : ViewModel(
) {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundel?>()
    val bundel: LiveData<NavigateWithBundel?> = _bundle

    fun getData() {
        val listItems = listOf<ItemsModel>()
    }

    fun imageViewClicked() {
        _msg.value = R.string.imageview_clicked

    }

    fun elementClicked(name: String, date: String, imageView: Int) {
        _bundle.value = NavigateWithBundel(
            name = name,
            data = date,
            image = imageView
        )
    }

    fun userNavigated() {
        _bundle.value = null
    }
}

data class NavigateWithBundel(
    val image: Int,
    val name: String,
    val data: String
)

class TestParametr()


