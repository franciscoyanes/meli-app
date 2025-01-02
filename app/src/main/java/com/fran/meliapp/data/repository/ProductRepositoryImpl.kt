package com.fran.meliapp.data.repository

import android.util.Log
import com.fran.meliapp.data.domain.model.ProductDescription
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.data.remote.MeliApi
import com.fran.meliapp.data.remote.dto.toProduct
import com.fran.meliapp.data.remote.dto.toProductDescription
import com.fran.meliapp.domain.repository.ProductRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepositoryImpl @Inject constructor(
    private val api: MeliApi
) : ProductRepository {

    override suspend fun searchProducts(query: String): List<ProductListingItem> {
        val response = api.searchProducts(query)
        if (response.isSuccessful) {
            val products = response.body()!!.results.map { it.toProduct() }
            return products
        } else {
            Log.e("ProductRepositoryImpl", "Error: ${response.message()}")
            throw RuntimeException("Error: ${response.message()}")
        }
    }

    override suspend fun getProductDescription(itemId: String): ProductDescription {
        val response = api.getProductDescription(itemId)
        if (response.isSuccessful) {
            val productDescription = response.body()!!.toProductDescription()
            return productDescription
        } else {
            Log.e("ProductRepositoryImpl", "Error: ${response.message()}")
            throw RuntimeException("Error: ${response.message()}")
        }
    }
}
