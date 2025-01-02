package com.fran.meliapp.domain.use_case

import android.util.Log
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.fran.meliapp.data.domain.model.ProductDescription
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

class GetProductDetailUseCaseTest {

    private lateinit var getProductDetailUseCase: GetProductDetailUseCase
    private lateinit var repositoryFake: ProductRepositoryFake

    @Before
    fun setUp() {
        repositoryFake = ProductRepositoryFake()
        getProductDetailUseCase = GetProductDetailUseCase(repositoryFake)
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any())} returns 0
    }

    @Test
    fun `Invoke returns success when repository returns data`() = runTest {
        // Given
        val testId = "test id"
        val expectedDescription = ProductDescription("test description")
        repositoryFake.addFakeDescription(testId, expectedDescription)

        // When
        val resultFlow = getProductDetailUseCase("test id").toList()

        // Then
        assertThat(resultFlow[1].data).isEqualTo(expectedDescription)

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
        val resultFlow = getProductDetailUseCase("test id").toList()

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
        val resultFlow = getProductDetailUseCase("test id").toList()

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
        val resultFlow = getProductDetailUseCase("test id").toList()

        // Then
        assertThat(resultFlow[1].message).isEqualTo(exception.message)
    }
}
