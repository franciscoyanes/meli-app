package com.fran.meliapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fran.meliapp.databinding.FragmentProductListingBinding
import com.fran.meliapp.presentation.ProductListingAdapter
import com.fran.meliapp.presentation.ProductListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : Fragment() {

    private var _binding: FragmentProductListingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListingViewModel by viewModels()
    private lateinit var adapter: ProductListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            adapter = ProductListingAdapter(products)
            binding.productListingRv.adapter = adapter
            binding.productListingRv.layoutManager = GridLayoutManager(
                requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter.setOnItemClickListener {
                Toast.makeText(requireContext(), "CLICK", Toast.LENGTH_SHORT).show()
            }
        }

//
//        var productList = mutableListOf(
//            ProductListingItem("Producto 1"),
//            ProductListingItem("Producto 2"),
//            ProductListingItem("Producto 3"),
//            ProductListingItem("Producto 4"),
//            ProductListingItem("Producto 5"),
//            ProductListingItem("Producto 6"),
//            ProductListingItem("Producto 7"),
//            ProductListingItem("Producto 8"),
//            ProductListingItem("Producto 9"),
//            ProductListingItem("Producto 10"),
//            ProductListingItem("Producto 11"),
//            ProductListingItem("Producto 12"),
//            ProductListingItem("Producto 13"),
//            ProductListingItem("Producto 14"),
//            ProductListingItem("Producto 15"),
//        )
//        val adapter = ProductListingAdapter(productList)
//        binding.productListingRv.adapter = adapter
//        binding.productListingRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
