package com.fran.meliapp.domain.repository

import com.fran.meliapp.data.domain.model.ProductDescription
import com.fran.meliapp.data.domain.model.ProductListingItem

interface ProductRepository {

    suspend fun searchProducts(query: String): List<ProductListingItem>

    suspend fun getProductDescription(itemId: String): ProductDescription
}
