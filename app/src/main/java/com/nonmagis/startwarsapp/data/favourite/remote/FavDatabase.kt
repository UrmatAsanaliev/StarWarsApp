package com.nonmagis.startwarsapp.data.favourite.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nonmagis.startwarsapp.data.favourite.model.FavouriteDto
import com.nonmagis.startwarsapp.data.people.model.Result


@Database(entities = [FavouriteDto::class], version = 1)
abstract class FavDatabase: RoomDatabase() {

    abstract fun dao(): FavDao
}