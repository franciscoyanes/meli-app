package com.fran.meliapp.domain.use_case

import com.fran.meliapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {

}
