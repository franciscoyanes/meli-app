package com.fran.meliapp.domain.use_case

import android.util.Log
import com.fran.meliapp.common.Constants
import com.fran.meliapp.common.Resource
import com.fran.meliapp.data.domain.model.ProductDescription
import com.fran.meliapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(productId: String): Flow<Resource<ProductDescription>> = flow {
        try {
            emit(Resource.Loading())
            val productDescription = repository.getProductDescription(productId)
            emit(Resource.Success(productDescription))
        } catch (e: IOException) {
            Log.e("SearchProductUseCase", e.message, e)
            emit(Resource.Error(e.localizedMessage ?: Constants.GENERIC_IO_ERROR_MSG))
        } catch (e: RuntimeException) {
            Log.e("SearchProductUseCase", e.message, e)
            emit(Resource.Error(e.message ?: Constants.UNKNOWN_ERROR_MSG))
        }
    }
}
