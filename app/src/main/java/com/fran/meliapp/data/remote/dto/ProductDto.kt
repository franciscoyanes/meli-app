package com.fran.meliapp.data.remote.dto


import com.fran.meliapp.data.domain.model.ProductListingItem
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("seller")
    val seller: Seller,
    @SerializedName("available_quantity")
    val quantity: Long
//    @SerializedName("currency_id")
//    val currencyId: String,
//    @SerializedName("available_quantity")
//    val availableQuantity: Int,
//    @SerializedName("buying_mode")
//    val buyingMode: String,
//    @SerializedName("listing_type_id")
//    val listingTypeId: String,
//    @SerializedName("stop_time")
//    val stopTime: String,
//    @SerializedName("condition")
//    val condition: String,
//    @SerializedName("permalink")
//    val permalink: String,
//    @SerializedName("accepts_mercadopago")
//    val acceptsMercadopago: Boolean,
//    @SerializedName("installments")
//    val installments: Installments,
//    @SerializedName("shipping")
//    val shipping: Shipping,
//    @SerializedName("attributes")
//    val attributes: List<Attribute?>,
//    @SerializedName("original_price")
//    val originalPrice: Any?,
//    @SerializedName("category_id")
//    val categoryId: String,
//    @SerializedName("official_store_id")
//    val officialStoreId: Int,
//    @SerializedName("catalog_product_id")
//    val catalogProductId: String,
//    @SerializedName("catalog_listing")
//    val catalogListing: Boolean
) {
//    data class Installments(
//        @SerializedName("quantity")
//        val quantity: Int,
//        @SerializedName("amount")
//        val amount: Double,
//        @SerializedName("rate")
//        val rate: Double,
//        @SerializedName("currency_id")
//        val currencyId: String
//    )
//
//    data class Shipping(
//        @SerializedName("free_shipping")
//        val freeShipping: Boolean,
//        @SerializedName("mode")
//        val mode: String,
//        @SerializedName("tags")
//        val tags: List<Any?>,
//        @SerializedName("logistic_type")
//        val logisticType: String,
//        @SerializedName("store_pick_up")
//        val storePickUp: Boolean
//    )
//
//    data class Attribute(
//        @SerializedName("name")
//        val name: String,
//        @SerializedName("value_id")
//        val valueId: String,
//        @SerializedName("value_name")
//        val valueName: String,
//        @SerializedName("value_struct")
//        val valueStruct: Any?,
//        @SerializedName("attribute_group_id")
//        val attributeGroupId: String,
//        @SerializedName("attribute_group_name")
//        val attributeGroupName: String,
//        @SerializedName("source")
//        val source: Int,
//        @SerializedName("id")
//        val id: String
//    )

    data class Address(
        @SerializedName("state_name")
        val stateName: String,
        @SerializedName("city_name")
        val cityName: String
    )

    data class Seller(
        @SerializedName("nickname")
        val nickname: String
    )
}

fun ProductDto.toProduct(): ProductListingItem {
    return ProductListingItem(
        id = id,
        title = title,
        thumbnail = thumbnail,
        stateName = address.stateName,
        city = address.cityName,
        sellerNickname = seller.nickname,
        price = price,
        quantity = quantity
    )
}
