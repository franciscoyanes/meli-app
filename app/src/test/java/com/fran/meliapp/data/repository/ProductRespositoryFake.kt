package com.fran.meliapp.data.repository

import com.fran.meliapp.data.domain.model.ProductDescription
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.domain.repository.ProductRepository

class ProductRespositoryFake : ProductRepository {

    private val fakeProducts = mutableListOf<ProductListingItem>()
    private val fakeProductDescriptions = mutableMapOf<String, ProductDescription>()

    fun addFakeProduct(product: ProductListingItem) {
        fakeProducts.add(product)
    }

    fun addFakeProducts(products: List<ProductListingItem>) {
        fakeProducts.addAll(products)
    }

    override suspend fun searchProducts(query: String): List<ProductListingItem> {
        return fakeProducts
    }

    override suspend fun getProductDescription(itemId: String): ProductDescription {
        return fakeProductDescriptions[itemId] ?: ProductDescription(description = "")
    }

}
