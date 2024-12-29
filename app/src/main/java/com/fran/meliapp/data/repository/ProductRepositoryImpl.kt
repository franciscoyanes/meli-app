package com.fran.meliapp.data.repository

import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.data.remote.MeliApi
import com.fran.meliapp.data.remote.dto.toProduct
import com.fran.meliapp.domain.repository.ProductRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepositoryImpl @Inject constructor(
    private val api: MeliApi
) : ProductRepository {

    override suspend fun searchProducts(query: String): List<ProductListingItem> {
        val response = api.searchProducts(query)
        val products = response.body()!!.results.map { it.toProduct() }
        return products
    }
}
