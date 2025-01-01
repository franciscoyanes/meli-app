package com.fran.meliapp.presentation.product_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import com.fran.meliapp.common.util.StringUtils
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.databinding.ViewProductListingItemBinding

class ProductListingAdapter(
    private var productList: List<ProductListingItem>
) : RecyclerView.Adapter<ProductListingAdapter.ProductListingViewHolder>() {

    private var onItemClickListener: ((ProductListingItem) -> Unit)? = null

    inner class ProductListingViewHolder(
        val binding: ViewProductListingItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListingViewHolder {
        val binding = ViewProductListingItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListingViewHolder, position: Int) {
        holder.binding.apply {
            val productItem = productList[position]
            productItemTitle.text = productItem.title
            productItemSellerName.text = productItem.sellerNickname
            productItemSellerAddress.text = productItem.stateName
            productItemPrice.text = StringUtils.floatToPriceString(productItem.price)
            productItemImg.load(
                // This is necessary because Coil client doesn't support unsecure calls.
                // TODO: Loading and error icons
                productItem.thumbnail.replace("http://", "https://")
            ) {
                crossfade(true)
                error(android.R.drawable.stat_notify_error)
            }
            root.setOnClickListener {
                onItemClickListener?.let { it(productItem) }
            }
        }
    }

    fun setOnItemClickListener(listener: (ProductListingItem) -> Unit) {
        onItemClickListener = listener
    }
}
