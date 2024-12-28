package com.fran.meliapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SearchProductResponseDto(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("results")
    val results: List<ProductDto>,
    @SerializedName("sort")
    val sort: Sort,
    @SerializedName("available_sorts")
    val availableSorts: List<Any?>,
    @SerializedName("filters")
    val filters: List<Any?>,
    @SerializedName("available_filters")
    val availableFilters: List<Any?>,
    @SerializedName("pdp_tracking")
    val pdpTracking: PdpTracking,
    @SerializedName("user_context")
    val userContext: Any?
) {
    data class Paging(
        @SerializedName("total")
        val total: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("primary_results")
        val primaryResults: Int
    )

    class Sort

    class PdpTracking
}
