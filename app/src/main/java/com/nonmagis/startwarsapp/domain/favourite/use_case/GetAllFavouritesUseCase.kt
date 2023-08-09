package com.nonmagis.startwarsapp.domain.favourite.use_case

import com.nonmagis.startwarsapp.domain.favourite.repo.FavRepository

class GetAllFavouritesUseCase(
    private val repo: FavRepository
) {

    suspend operator fun invoke() = repo.getAllFavourites()
}