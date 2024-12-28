package com.fran.meliapp.data.remote

import com.fran.meliapp.data.remote.dto.SearchProductResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliApi {

    @GET("sites/MLA/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): Response<SearchProductResponseDto>
}