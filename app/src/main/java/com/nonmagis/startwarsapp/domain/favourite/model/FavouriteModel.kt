package com.nonmagis.startwarsapp.domain.favourite.model

data class FavouriteModel(
    var id: Int = 0,
    var name: String = "",
    var gender: String = "",
    var starships_count: String = "",
    var model: String = "",
    var manufacturer: String = "",
    var passengers: String = ""
)