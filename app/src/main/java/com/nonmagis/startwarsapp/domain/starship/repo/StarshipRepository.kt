package com.nonmagis.startwarsapp.domain.starship.repo

import com.nonmagis.startwarsapp.core.Resource
import com.nonmagis.startwarsapp.domain.starship.model.Starship
import kotlinx.coroutines.flow.Flow

interface StarshipRepository {

    fun getStarships(model: String): Flow<Resource<List<Starship>>>
}