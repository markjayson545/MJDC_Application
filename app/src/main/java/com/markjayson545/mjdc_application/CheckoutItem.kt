package com.markjayson545.mjdc_application

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class CheckoutItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val productIcon: ImageView
    private val productTitle: TextView
    private val productQuantity: TextView
    private val productPrice: TextView

    init {
        inflate(context, R.layout.checkout_item, this)

        productIcon = findViewById(R.id.custom_button_icon)
        productTitle = findViewById(R.id.productTitle)
        productQuantity = findViewById(R.id.productQuantity)
        productPrice = findViewById(R.id.productPrice)
    }

    fun setProductData(title: String, quantity: Int, price: String, iconResId: Int? = null) {
        productTitle.text = title
        productQuantity.text = "Qty: $quantity"
        productPrice.text = price
        iconResId?.let { productIcon.setImageResource(it) }
    }
}