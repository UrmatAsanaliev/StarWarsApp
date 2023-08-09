package com.nonmagis.startwarsapp.domain.people.repo

import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.domain.people.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPerson(name: String): Flow<Resource<List<ResultModel>>>
}