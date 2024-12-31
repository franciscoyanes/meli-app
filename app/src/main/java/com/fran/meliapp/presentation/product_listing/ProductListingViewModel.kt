package com.fran.meliapp.presentation.product_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fran.meliapp.common.Resource
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.domain.use_case.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _productsLiveData = MutableLiveData<List<ProductListingItem>>()
    val productsLiveData: LiveData<List<ProductListingItem>> = _productsLiveData

    init {
        val queryString = savedStateHandle.get<String>("queryString")
        fetchProducts(queryString!!)
    }

    private fun fetchProducts(query: String) {
        searchProductUseCase(query).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _productsLiveData.value = result.data ?: emptyList()
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                    _productsLiveData.value = emptyList()
                }
            }
        }.launchIn(viewModelScope)
        // The invoke function returns a Flow, so we need to launch it in a coroutine because
        // flows are async.
    }
}
