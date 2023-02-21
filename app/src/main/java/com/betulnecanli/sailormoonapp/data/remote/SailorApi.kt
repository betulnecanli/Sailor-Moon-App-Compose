package com.betulnecanli.sailormoonapp.data.remote

import com.betulnecanli.sailormoonapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SailorApi {


    @GET("/sailormoon/characters")
    suspend fun getCharacters(
        @Query("page") page : Int = 1
    ) : ApiResponse



    @GET("/sailormoon/characters/search")
    suspend fun searchCharacters(
        @Query("name") name : String
    ): ApiResponse

}