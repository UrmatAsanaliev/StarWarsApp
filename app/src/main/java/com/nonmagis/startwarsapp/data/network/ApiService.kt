package com.nonmagis.startwarsapp.data.network

import com.nonmagis.startwarsapp.data.people.model.PeopleDto
import com.nonmagis.startwarsapp.data.people.model.Result
import com.nonmagis.startwarsapp.data.starships.model.StarshipDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("people/")
    suspend fun getPerson(
        @Query("search") name: String = ""
    ): PeopleDto<Result>


    @GET("starships/")
    suspend fun getStarships(
        @Query("search") name: String = ""
    ): PeopleDto<StarshipDto>
}