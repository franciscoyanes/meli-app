package com.fran.meliapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fran.meliapp.R
import com.fran.meliapp.databinding.FragmentProductListingBinding
import com.fran.meliapp.presentation.ProductListingAdapter
import com.fran.meliapp.presentation.ProductListingItem

class ProductListingFragment : Fragment() {

    private var _binding: FragmentProductListingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var productList = mutableListOf(
            ProductListingItem("Producto 1"),
            ProductListingItem("Producto 2"),
            ProductListingItem("Producto 3"),
            ProductListingItem("Producto 4"),
            ProductListingItem("Producto 5"),
            ProductListingItem("Producto 6"),
            ProductListingItem("Producto 7"),
            ProductListingItem("Producto 8"),
            ProductListingItem("Producto 9"),
            ProductListingItem("Producto 10"),
            ProductListingItem("Producto 11"),
            ProductListingItem("Producto 12"),
            ProductListingItem("Producto 13"),
            ProductListingItem("Producto 14"),
            ProductListingItem("Producto 15"),
        )
        val adapter = ProductListingAdapter(productList)
        binding.productListingRv.adapter = adapter
        binding.productListingRv.layoutManager = LinearLayoutManager(requireContext())
    }
}
