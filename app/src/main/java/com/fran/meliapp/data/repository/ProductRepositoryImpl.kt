package com.fran.meliapp.data.repository

import com.fran.meliapp.data.remote.dto.SearchProductResponseDto
import com.fran.meliapp.domain.repository.ProductRepository
import com.fran.meliapp.data.remote.MeliApi
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class ProductRepositoryImpl @Inject constructor(
    private val api: MeliApi
) : ProductRepository {

    override suspend fun searchProducts(query: String): Response<SearchProductResponseDto> {
        val response = api.searchProducts(query)
        return response
    }
}
