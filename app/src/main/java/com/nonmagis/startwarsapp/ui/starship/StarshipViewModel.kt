package com.nonmagis.startwarsapp.ui.starship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.core.UIState
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.favourite.use_case.SetFavouritesUseCase
import com.nonmagis.startwarsapp.domain.starship.model.Starship
import com.nonmagis.startwarsapp.domain.starship.use_case.GetStarshipsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StarshipViewModel(
    private val getStarshipsUseCase: GetStarshipsUseCase,
    private val setFavouritesUseCase: SetFavouritesUseCase
): ViewModel() {

    private val _data = MutableStateFlow<UIState<List<Starship>>>(UIState.Loading())
    val data = _data.asStateFlow()

    fun getStarship(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getStarshipsUseCase(name).collect {
                when (it) {
                    is Resource.Error -> {
                        _data.value = UIState.Error(it.message ?: "Unknown error")
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