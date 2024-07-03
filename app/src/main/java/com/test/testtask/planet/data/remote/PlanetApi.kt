package com.test.testtask.planet.data.remote

import com.test.testtask.planet.data.dto.Planet
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {

    @GET("api/planets/{id}")
    suspend fun getPlanet(@Path("id") id: Int): Planet

}