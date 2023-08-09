package com.nonmagis.startwarsapp.data.people.model

data class PeopleDto<T>(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<T>
)