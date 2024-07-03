package com.test.testtask.characters.data.remote

import com.test.testtask.characters.data.dto.Character
import com.test.testtask.common.ApiResponse
import retrofit2.http.GET

interface CharactersApi {

    @GET("api/people/")
    suspend fun getCharacters(): ApiResponse<Character>

}