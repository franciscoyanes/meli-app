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
) {

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
