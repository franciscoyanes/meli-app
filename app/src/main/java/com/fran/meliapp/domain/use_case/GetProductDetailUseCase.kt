package com.fran.meliapp.domain.use_case

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
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Constants.GENERIC_HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error(Constants.GENERIC_IO_ERROR_MSG))
        }
    }
}
