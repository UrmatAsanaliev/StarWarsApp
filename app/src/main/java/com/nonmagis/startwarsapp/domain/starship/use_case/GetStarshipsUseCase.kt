package com.nonmagis.startwarsapp.domain.starship.use_case

import com.nonmagis.startwarsapp.domain.starship.repo.StarshipRepository

class GetStarshipsUseCase(
    private val repo: StarshipRepository
) {

    operator fun invoke(model: String) = repo.getStarships(model)
}