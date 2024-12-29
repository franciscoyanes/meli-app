package com.fran.meliapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.network.httpMethod
import coil3.request.crossfade
import com.fran.meliapp.data.domain.model.ProductListingItem
import com.fran.meliapp.databinding.ViewProductListingItemBinding

class ProductListingAdapter(
    private var productList: List<ProductListingItem>
) : RecyclerView.Adapter<ProductListingAdapter.ProductListingViewHolder>() {

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
            productItemTitle.text = productList[position].title
            productItemImg.load(
                // This is necessary Coil client doesn't support unsecure calls.
                // TODO: Loading and error icons
                productList[position].thumbnail.replace("http://", "https://")
            ) {
                crossfade(true)
            }
        }
    }
}
