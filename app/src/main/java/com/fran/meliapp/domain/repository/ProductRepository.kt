package com.fran.meliapp.domain.repository

import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.data.remote.dto.SearchProductResponseDto
import retrofit2.Response

interface ProductRepository {

    suspend fun searchProducts(query: String): List<ProductListingItem>
}
