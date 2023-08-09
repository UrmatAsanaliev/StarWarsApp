package com.nonmagis.startwarsapp.domain.people.use_case

import com.nonmagis.startwarsapp.domain.people.repo.PersonRepository

class GetPersonUseCase(
    private val repo: PersonRepository
) {

    operator fun invoke(name: String) = repo.getPerson(name)

}