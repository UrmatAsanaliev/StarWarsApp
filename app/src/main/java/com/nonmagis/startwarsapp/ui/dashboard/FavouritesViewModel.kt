package com.nonmagis.startwarsapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.core.UIState
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.favourite.use_case.GetAllFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase
) : ViewModel() {


    private val _data = MutableStateFlow<UIState<List<FavouriteModel>>>(UIState.Loading())
    val data = _data.asStateFlow()

    fun getAllFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavouritesUseCase().collect {
                when (it) {
                    is Resource.Error -> {
                        _data.value = UIState.Error(it.message?: "Unknown error")
                    }
                    is Resource.Loading -> {
                        _data.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        _data.value = UIState.Success(it.data!!)
                    }
                }
            }
        }
    }
}