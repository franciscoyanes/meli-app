package com.fran.meliapp.data.domain.model

import java.io.Serializable

data class ProductListingItem(
    val id: String,
    val title: String,
    val thumbnail: String,
    val stateName: String,
    val city: String,
    val sellerNickname: String,
    val price: Float,
    val quantity: Long
) : Serializable
