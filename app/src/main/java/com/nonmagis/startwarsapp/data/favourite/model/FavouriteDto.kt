package com.nonmagis.startwarsapp.data.favourite.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel

@Entity
data class FavouriteDto(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var gender: String = "",
    var starships_count: String = "",
    var model: String = "",
    var manufacturer: String = "",
    var passengers: String = ""
)

fun FavouriteDto.toDomain() = FavouriteModel(
    id, name, gender, starships_count, model, manufacturer, passengers
)

fun FavouriteModel.toData() = FavouriteDto(
    id, name, gender, starships_count, model, manufacturer, passengers
)