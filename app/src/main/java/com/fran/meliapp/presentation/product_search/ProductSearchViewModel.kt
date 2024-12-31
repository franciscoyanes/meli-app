package com.fran.meliapp.presentation.product_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO: Decide if ViewModel should be in charge of the event listener
@HiltViewModel
class ProductSearchViewModel @Inject constructor() : ViewModel() {

    private var onSearchActionEventListener: ((String) -> Unit)? = null

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    init {

    }

    fun setSearchActionEventListener(listener: (String) -> Unit) {
        onSearchActionEventListener = listener
    }
}
