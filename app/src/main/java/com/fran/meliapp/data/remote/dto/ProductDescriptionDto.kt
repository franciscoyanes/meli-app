package com.fran.meliapp.data.remote.dto

import com.fran.meliapp.data.domain.model.ProductDescription
import com.google.gson.annotations.SerializedName

data class ProductDescriptionDto(
    @SerializedName("plain_text")
    val description: String
)

fun ProductDescriptionDto.toProductDescription(): ProductDescription {
    return ProductDescription(
        description = description
    )
}
