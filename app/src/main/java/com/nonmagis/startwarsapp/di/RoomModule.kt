package com.nonmagis.startwarsapp.di

import android.content.Context
import androidx.room.Room
import com.nonmagis.startwarsapp.data.favourite.remote.FavDao
import com.nonmagis.startwarsapp.data.favourite.remote.FavDatabase
//import com.nonmagis.startwarsapp.data.remote.FavDao
//import com.nonmagis.startwarsapp.data.remote.FavDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {  provideRoomDatabase(androidApplication()) }
    single<FavDao> {
        val database = get<FavDatabase>()
        database.dao()
    }
}

fun provideRoomDatabase(context: Context) =
    Room.databaseBuilder(
        context,
        FavDatabase::class.java,
        "database"
    ).allowMainThreadQueries().build()