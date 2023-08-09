package com.nonmagis.startwarsapp.data.favourite.repo

import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.data.favourite.model.toData
import com.nonmagis.startwarsapp.data.favourite.model.toDomain
import com.nonmagis.startwarsapp.data.favourite.remote.FavDao
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.favourite.repo.FavRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FavouriteRepositoryImpl(
    private val dao: FavDao
) : FavRepository {
    override suspend fun getAllFavourites(): Flow<Resource<List<FavouriteModel>>> = flow {
        try {
            emit(Resource.Loading())
            val data = dao.getAllFavourites().map { it.toDomain() }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }

    override suspend fun setFavourite(model: FavouriteModel): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val data = dao.setFavourite(model.toData())
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }

    override suspend fun deleteFavourite(model: FavouriteModel): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val data = dao.deleteFavourite(model.toData())
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }
}