package com.fran.meliapp.presentation.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil3.load
import coil3.request.crossfade
import com.fran.meliapp.common.Constants
import com.fran.meliapp.common.util.StringUtils
import com.fran.meliapp.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

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
            binding.apply {
                productDetailTitle.text = product.title.ifEmpty { Constants.EMPTY_DATA_MSG }
                productDetailPrice.text = StringUtils.floatToPriceString(product.price)
                    .ifEmpty { Constants.EMPTY_DATA_MSG }
                productDetailImg.load(
                    product.thumbnail.replace("http://", "https://")
                ) {
                    crossfade(true)
//                    error(android.R.drawable.stat_notify_error) // TODO: fix bug
                }
                productDetailQuantity.text = String.format(Locale.US, "%d", product.quantity)
                    .ifEmpty { Constants.EMPTY_DATA_MSG }
                productDetailSeller.text = product.sellerNickname.ifEmpty { Constants.EMPTY_DATA_MSG }
                productDetailAddress.text = String.format(
                    Locale.US,
                    "%s, %s",
                    product.city,
                    product.stateName
                ).ifEmpty { Constants.EMPTY_DATA_MSG }
            }
        }
        viewModel.productDescription.observe(viewLifecycleOwner) { description ->
            if (description.isEmpty()) {
                binding.root.veil()
            } else {
                binding.root.unVeil()
                binding.productDetailDescription.text = description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
