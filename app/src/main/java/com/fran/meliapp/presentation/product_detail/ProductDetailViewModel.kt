package com.fran.meliapp.presentation.product_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fran.meliapp.data.domain.model.ProductListingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _product = savedStateHandle.getLiveData<ProductListingItem>("product")
    val product: LiveData<ProductListingItem> = _product

    init {
        val asd = savedStateHandle.get<ProductListingItem>("product")
    }


}