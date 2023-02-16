package com.example.androidproject.presentation.view.view.auth.home.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.R
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.domain.model.ItemsModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class ItemsViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) : ViewModel(
) {


    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _trigger = MutableLiveData<Flow<Unit>>()
    val trigger = _trigger


    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundel?>()
    val bundel: LiveData<NavigateWithBundel?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val compositeDisposable = CompositeDisposable()


    fun getData(){
       val getData = itemsInteractor.getData().subscribe({

        }, {

        })
        compositeDisposable.add(getData)

       val showData = itemsInteractor.showData().subscribe({
            _items.value = it
        },{
            _error.value = "Error occured while showing data"
        })
        compositeDisposable.add(showData)
    }

//    fun getData() {
//        viewModelScope.launch {
//            try {
//                itemsInteractor.getData()
//            } catch (e: Exception) {
//                _error.value = e.message.toString()
//            }
//        }
//        viewModelScope.launch {
//            try {
//                val listItems = itemsInteractor.showData()
//                listItems.collect{
//                    _items.value = it
//                }
//            } catch (e: Exception) {
//                _error.value = e.message.toString()
//            }
//        }


    suspend fun getDataSimple(){
        itemsInteractor.getData()
    }



    fun imageViewClicked() {
        _msg.value = R.string.imageview_clicked

    }

    fun elementClicked(description: String, image: String) {
        _bundle.value = NavigateWithBundel(
            description, image, R.id.action_itemsFragment_to_detailsFragment
        )
    }

    fun deleteItem(description: String) {
        viewModelScope.launch {
            itemsInteractor.deleteItemByDescription(description)
        }
    }

    fun onFavClicked(description: String, isFavorite: Boolean) {
        viewModelScope.launch {
            itemsInteractor.onFavClicked(description, isFavorite)
        }
    }

    fun userNavigated() {
        _bundle.value = null
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

data class NavigateWithBundel(
    val description: String,
    val image: String,
    val destinationId: Int
)





