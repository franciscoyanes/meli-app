package com.fran.meliapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fran.meliapp.R

class ProductListingAdapter(
    private var productList: List<ProductListingItem>
) : RecyclerView.Adapter<ProductListingAdapter.ProductListingViewHolder>() {

    inner class ProductListingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_product_listing_item, parent, false)
        return ProductListingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListingViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.product_listing_item_title).text = productList[position].title
        }
    }
}
