package com.fran.meliapp.domain.use_case

import android.util.Log
import com.fran.meliapp.common.Constants
import com.fran.meliapp.common.Resource
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /*
    * Normally Use Cases should only have one public function called "execute". Kotlin lets me
    * overload the invoke operator function so we can call the class as a function.
    *
    * The Resource class will let us handle Success, Error and Loading states.
    */
    operator fun invoke(query: String): Flow<Resource<List<ProductListingItem>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.searchProducts(query)
            emit(Resource.Success(products))
        } catch (e: HttpException) {
            Log.e("SearchProductUseCase", e.message, e)
            emit(Resource.Error(e.localizedMessage ?: Constants.GENERIC_HTTP_ERROR_MSG))
        } catch (e: IOException) {
            Log.e("SearchProductUseCase", e.message, e)
            emit(Resource.Error(e.localizedMessage ?: Constants.GENERIC_IO_ERROR_MSG))
        } catch (e: RuntimeException) {
            Log.e("SearchProductUseCase", e.message, e)
            emit(Resource.Error(e.message ?: Constants.UNKNOWN_ERROR_MSG))
        }
    }
}
