package com.nonmagis.startwarsapp.domain.favourite.repo

import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.people.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface FavRepository {

    suspend fun getAllFavourites(): Flow<Resource<List<FavouriteModel>>>

    suspend fun setFavourite(model: FavouriteModel): Flow<Resource<Unit>>

    suspend fun deleteFavourite(model: FavouriteModel):  Flow<Resource<Unit>>
}