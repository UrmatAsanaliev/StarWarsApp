package com.nonmagis.startwarsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.core.UIState
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.favourite.use_case.SetFavouritesUseCase
import com.nonmagis.startwarsapp.domain.people.model.ResultModel
import com.nonmagis.startwarsapp.domain.people.use_case.GetPersonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPersonUseCase: GetPersonUseCase,
    private val setFavouritesUseCase: SetFavouritesUseCase
) : ViewModel() {

    private val _data = MutableStateFlow<UIState<List<ResultModel>>>(UIState.Loading())
    val data = _data.asStateFlow()

    fun getPerson(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPersonUseCase(name).collect {
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


    private val _result = MutableStateFlow<UIState<Unit>>(UIState.Loading())
    val result = _result.asStateFlow()

    fun setFavourites(model: FavouriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            setFavouritesUseCase(model).collect {
                when (it) {
                    is Resource.Error -> {
                        _result.value = UIState.Error(it.message?: "Unknown error")
                    }
                    is Resource.Loading -> {
                        _result.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        _result.value = UIState.Success(it.data!!)
                    }
                }
            }
        }
    }
}