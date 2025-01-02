package com.fran.meliapp.data.repository

import assertk.assertions.isEmpty
import com.fran.meliapp.data.remote.MeliApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductRepositoryImplTest {

    private lateinit var repository: ProductRepositoryImpl
    private lateinit var api: MeliApi
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()
        repository = ProductRepositoryImpl(api)
    }

    @org.junit.jupiter.api.Test
    fun `basic test`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                {
                    "site_id": "MLA",
                    "country_default_time_zone": "GMT-03:00",
                    "query": "a",
                    "paging": {
                        "total": 0,
                        "primary_results": 0,
                        "offset": 0,
                        "limit": 50
                    },
                    "results": [],
                    "sort": {
                        "id": "relevance",
                        "name": "Más relevantes"
                    },
                    "available_sorts": [
                        {
                            "id": "price_asc",
                            "name": "Menor precio"
                        },
                        {
                            "id": "price_desc",
                            "name": "Mayor precio"
                        }
                    ],
                    "filters": [],
                    "available_filters": [],
                    "pdp_tracking": {
                        "group": false,
                        "product_info": []
                    },
                    "user_context": null
                }
            """.trimIndent()
                )
        )

        val products = repository.searchProducts("a")
        assertk.assertThat(products).isEmpty()
    }
}