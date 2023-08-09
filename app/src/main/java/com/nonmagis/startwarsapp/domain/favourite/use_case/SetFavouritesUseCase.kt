package com.nonmagis.startwarsapp.domain.favourite.use_case

import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.favourite.repo.FavRepository

class SetFavouritesUseCase(
    private val repo: FavRepository
) {
    suspend operator fun invoke(model: FavouriteModel) = repo.setFavourite(model)
}