package com.fran.meliapp.data.repository

import com.fran.meliapp.data.domain.model.ProductDescription
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.domain.repository.ProductRepository
import java.lang.Exception

class ProductRepositoryFake : ProductRepository {

    private val fakeProducts = mutableListOf<ProductListingItem>()
    private val fakeProductDescriptions = mutableMapOf<String, ProductDescription>()

    var throwsException = false
    private var _exception = Exception()

    override suspend fun searchProducts(query: String): List<ProductListingItem> {
        if (throwsException) {
            throw _exception
        }
        return fakeProducts
    }

    override suspend fun getProductDescription(itemId: String): ProductDescription {
        if (throwsException) {
            throw _exception
        }
        return fakeProductDescriptions[itemId] ?: ProductDescription(description = "")
    }

    fun addFakeProduct(product: ProductListingItem) {
        fakeProducts.add(product)
    }

    fun addFakeProducts(products: List<ProductListingItem>) {
        fakeProducts.addAll(products)
    }

    fun addFakeDescription(itemId: String, description: ProductDescription) {
        fakeProductDescriptions[itemId] = description
    }

    fun setExceptionToThrow(exception: Exception) {
        _exception = exception
    }
}
