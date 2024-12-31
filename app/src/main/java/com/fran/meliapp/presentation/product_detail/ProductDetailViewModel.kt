package com.fran.meliapp.presentation.product_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fran.meliapp.common.Resource
import com.fran.meliapp.data.domain.model.ProductDescription
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.domain.use_case.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _product = savedStateHandle.getLiveData<ProductListingItem>("product")
    val product: LiveData<ProductListingItem> = _product
    private val _productDescription = MutableLiveData<String>("")
    val productDescription: LiveData<String> = _productDescription

    init {
        fetchProductDescription(product.value!!.id)
    }

    private fun fetchProductDescription(productId: String) {
        getProductDetailUseCase(productId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    val productDescription = result.data as ProductDescription
                    _productDescription.value = productDescription.description
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}
