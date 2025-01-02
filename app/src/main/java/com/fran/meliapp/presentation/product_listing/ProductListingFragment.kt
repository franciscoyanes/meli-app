package com.fran.meliapp.presentation.product_listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fran.meliapp.R
import com.fran.meliapp.databinding.FragmentProductListingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : Fragment() {

    private var _binding: FragmentProductListingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListingViewModel by viewModels()
    private lateinit var adapter: ProductListingAdapter

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
            binding.productListingRv.setAdapter(adapter)
            binding.productListingRv.setLayoutManager(
                GridLayoutManager(
                    requireContext(), 2, GridLayoutManager.VERTICAL, false
                )
            )
            adapter.setOnItemClickListener {
                val bundle = Bundle().apply {
                    putSerializable("product", it)
                }
                findNavController()
                    .navigate(
                        R.id.action_productListingFragment_to_productDetailFragment,
                        bundle
                    )
            }
            if (products.isEmpty()) {
                binding.productListingRv.addVeiledItems(6)
            } else {
                binding.productListingRv.unVeil()
            }
        }
        viewModel.loadingFinished.observe(viewLifecycleOwner) { isFinished ->
            if (isFinished) {
                if (viewModel.productsLiveData.value!!.isEmpty()) {
                    showEmptySearchResult()
                } else {
                    hideEmptySearchResult()
                    binding.productListingRv.unVeil()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideEmptySearchResult()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showEmptySearchResult() {
        binding.apply {
            productListingEmptySearchImg.visibility = View.VISIBLE
            productListingEmptySearchMessage.visibility = View.VISIBLE
            productListingEmptySearchHint.visibility = View.VISIBLE
            productListingRv.visibility = View.GONE
        }
    }

    private fun hideEmptySearchResult() {
        binding.apply {
            root.setBackgroundColor(resources.getColor(R.color.white))
            productListingEmptySearchImg.visibility = View.GONE
            productListingEmptySearchMessage.visibility = View.GONE
            productListingEmptySearchHint.visibility = View.GONE
            productListingRv.visibility = View.VISIBLE
        }
    }
}
