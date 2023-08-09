package com.nonmagis.startwarsapp.data.people.repo

import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.domain.people.model.ResultModel
import com.nonmagis.startwarsapp.data.people.model.toDomain
import com.nonmagis.startwarsapp.data.network.ApiService
import com.nonmagis.startwarsapp.domain.people.repo.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class PersonRepositoryImpl(
    private val api: ApiService
): PersonRepository {


    override fun getPerson(name: String): Flow<Resource<List<ResultModel>>> = flow {
        try {
            emit(Resource.Loading())
            val data = api.getPerson(name).results.map { it.toDomain() }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }
}