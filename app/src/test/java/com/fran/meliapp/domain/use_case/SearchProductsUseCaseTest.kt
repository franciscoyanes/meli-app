package com.fran.meliapp.domain.use_case

import android.util.Log
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.data.repository.ProductRepositoryFake
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SearchProductsUseCaseTest {

    private lateinit var searchProductUseCase: SearchProductUseCase
    private lateinit var repositoryFake: ProductRepositoryFake

    @Before
    fun setUp() {
        repositoryFake = ProductRepositoryFake()
        searchProductUseCase = SearchProductUseCase(repositoryFake)
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any())} returns 0
    }

    @Test
    fun `Invoke returns success when repository returns data`() = runTest {
        // Given
        val expectedProducts = listOf(
            ProductListingItem(
                id = "1",
                title = "Product 1",
                price = 100f,
                thumbnail = "https://example.com/product1.jpg",
                stateName = "Buenos Aires",
                city = "Capital Federal",
                sellerNickname = "Seller 1",
                quantity = 100
            ),
            ProductListingItem(
                id = "2",
                title = "Product 2",
                price = 200f,
                thumbnail = "https://example.com/product2.jpg",
                stateName = "Buenos Aires",
                city = "Capital Federal",
                sellerNickname = "Seller 2",
                quantity = 200
            )
        )
        repositoryFake.addFakeProducts(expectedProducts)

        // When
        val resultFlow = searchProductUseCase("query").toList()

        // Then
        assertThat(resultFlow[1].data).isEqualTo(expectedProducts)

    }

    @Test
    fun `Invoke emits error when repository throws HttpException`() = runTest {
        // Given
        repositoryFake.throwsException = true
        val exception = HttpException(
            Response.error<Any>(
                500,
                "{\"message\":\"test error\"}".toResponseBody("application/json".toMediaTypeOrNull())
            )
        )
        repositoryFake.setExceptionToThrow(exception)

        // When
        val resultFlow = searchProductUseCase("query").toList()

        // Then
        assertThat(resultFlow[1].message).isEqualTo(exception.message)
    }

    @Test
    fun `Invoke emits error when repository throws IOException`() = runTest {
        // Given
        repositoryFake.throwsException = true
        val exception = IOException("test error")
        repositoryFake.setExceptionToThrow(exception)

        // When
        val resultFlow = searchProductUseCase("query").toList()

        // Then
        assertThat(resultFlow[1].message).isEqualTo(exception.message)
    }

    @Test
    fun `Invoke emits error when repository throws RuntimeException`() = runTest {
        // Given
        repositoryFake.throwsException = true
        val exception = RuntimeException("test error")
        repositoryFake.setExceptionToThrow(exception)

        // When
        val resultFlow = searchProductUseCase("query").toList()

        // Then
        assertThat(resultFlow[1].message).isEqualTo(exception.message)
    }
}
