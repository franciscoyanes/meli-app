package com.fran.meliapp.presentation.product_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.fran.meliapp.databinding.FragmentProductSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchFragment : Fragment() {

    private var _binding: FragmentProductSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Android Views has a bug that focuses Edit Text elements automatically.
        binding.productSearchInput.clearFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}