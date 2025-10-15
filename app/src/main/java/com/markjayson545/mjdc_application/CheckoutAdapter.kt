package com.markjayson545.mjdc_application

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CheckoutAdapter(private val checkoutProducts: List<CheckoutProduct>) : RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>() {

    class CheckoutViewHolder(private val checkoutItem: CheckoutItem) : RecyclerView.ViewHolder(checkoutItem) {
        fun bind(checkoutProduct: CheckoutProduct) {
            checkoutItem.setProductData(
                title = checkoutProduct.name,
                quantity = checkoutProduct.quantity,
                price = "₱${checkoutProduct.price}",
                iconResId = checkoutProduct.imageResId
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val checkoutItem = CheckoutItem(parent.context)
        checkoutItem.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return CheckoutViewHolder(checkoutItem)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(checkoutProducts[position])
    }

    override fun getItemCount(): Int = checkoutProducts.size
}
