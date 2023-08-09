package com.nonmagis.startwarsapp.di


import com.nonmagis.startwarsapp.domain.favourite.use_case.DeleteFavouriteUseCase
import com.nonmagis.startwarsapp.domain.favourite.use_case.GetAllFavouritesUseCase
import com.nonmagis.startwarsapp.domain.favourite.use_case.SetFavouritesUseCase
import com.nonmagis.startwarsapp.domain.people.use_case.GetPersonUseCase
import com.nonmagis.startwarsapp.domain.starship.use_case.GetStarshipsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetPersonUseCase(get()) }
    factory { GetStarshipsUseCase(get()) }
    factory { GetAllFavouritesUseCase(get()) }
    factory { SetFavouritesUseCase(get()) }
    factory { DeleteFavouriteUseCase(get()) }
}