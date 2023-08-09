package com.nonmagis.startwarsapp.di


import com.nonmagis.startwarsapp.ui.dashboard.FavouritesViewModel
import com.nonmagis.startwarsapp.ui.home.HomeViewModel
import com.nonmagis.startwarsapp.ui.starship.StarshipViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { StarshipViewModel(get(), get()) }
    viewModel { FavouritesViewModel(get()) }
}