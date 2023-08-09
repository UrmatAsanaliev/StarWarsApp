package com.nonmagis.startwarsapp.di


import com.nonmagis.startwarsapp.data.favourite.repo.FavouriteRepositoryImpl
import com.nonmagis.startwarsapp.data.people.repo.PersonRepositoryImpl
import com.nonmagis.startwarsapp.data.starships.repo.StarshipRepositoryImpl
import com.nonmagis.startwarsapp.domain.favourite.repo.FavRepository
import com.nonmagis.startwarsapp.domain.people.repo.PersonRepository
import com.nonmagis.startwarsapp.domain.starship.repo.StarshipRepository
import org.koin.dsl.module

val repoModule = module {
    single<PersonRepository> { PersonRepositoryImpl(get()) }
    single<StarshipRepository> { StarshipRepositoryImpl(get()) }
    single<FavRepository> { FavouriteRepositoryImpl(get()) }
}
