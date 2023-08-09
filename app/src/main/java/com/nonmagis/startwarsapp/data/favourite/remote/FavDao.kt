package com.nonmagis.startwarsapp.data.favourite.remote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nonmagis.startwarsapp.data.favourite.model.FavouriteDto
import com.nonmagis.startwarsapp.data.people.model.Result
import com.nonmagis.startwarsapp.domain.people.model.ResultModel

@Dao
interface FavDao {

    @Query("SELECT * FROM favouritedto")
    suspend fun getAllFavourites(): List<FavouriteDto>

    @Insert
    suspend fun setFavourite(model: FavouriteDto)

    @Delete
    suspend fun deleteFavourite(model: FavouriteDto)
}