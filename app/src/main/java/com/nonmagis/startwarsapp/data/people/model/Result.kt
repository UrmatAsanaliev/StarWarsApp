package com.nonmagis.startwarsapp.data.people.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nonmagis.startwarsapp.domain.people.model.ResultModel

data class Result(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)

fun Result.toDomain() = ResultModel(
    birth_year,
    created,
    edited,
    eye_color,
    films,
    gender,
    hair_color,
    height,
    homeworld,
    mass,
    name,
    skin_color,
    species,
    starships,
    url,
    vehicles
)

fun ResultModel.toDomain() = Result(
    birth_year,
    created,
    edited,
    eye_color,
    films,
    gender,
    hair_color,
    height,
    homeworld,
    mass,
    name,
    skin_color,
    species,
    starships,
    url,
    vehicles
)