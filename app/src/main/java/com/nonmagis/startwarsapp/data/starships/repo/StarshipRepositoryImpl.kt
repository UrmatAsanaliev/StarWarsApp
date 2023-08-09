package com.nonmagis.startwarsapp.data.starships.repo

import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.data.network.ApiService
import com.nonmagis.startwarsapp.data.starships.model.toData
import com.nonmagis.startwarsapp.data.starships.model.toDomain
import com.nonmagis.startwarsapp.domain.starship.model.Starship
import com.nonmagis.startwarsapp.domain.starship.repo.StarshipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class StarshipRepositoryImpl(
    private val api: ApiService
): StarshipRepository {

    override fun getStarships(model: String): Flow<Resource<List<Starship>>> = flow {
        try {
            emit(Resource.Loading())
            val data = api.getStarships(model).results.map { it.toDomain() }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }
}