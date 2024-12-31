package com.fran.meliapp.presentation.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil3.load
import coil3.request.crossfade
import com.fran.meliapp.common.util.StringUtils
import com.fran.meliapp.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.product.observe(viewLifecycleOwner) { product ->
            binding.productDetailTitle.text = product.title
            binding.productDetailPrice.text = StringUtils.floatToPriceString(product.price)
            binding.productDetailImg.load(
                product.thumbnail.replace("http://", "https://")
            ) {
                crossfade(true)
            }
        }
        viewModel.productDescription.observe(viewLifecycleOwner) { description ->
            binding.productDetailDescription.text = description
        }
    }
}
