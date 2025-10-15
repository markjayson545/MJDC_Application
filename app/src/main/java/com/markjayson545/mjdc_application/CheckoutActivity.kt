package com.markjayson545.mjdc_application

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckoutActivity : AppCompatActivity() {
    private lateinit var backBtn: Button
    private lateinit var totalTxtView: TextView
    private lateinit var checkoutItemView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeIds()
        setupRecyclerView()
        backBtn.setOnClickListener {
            finish()
        }
        calculateTotal()
    }

    fun initializeIds() {
        backBtn = findViewById(R.id.backBtn1)
        checkoutItemView = findViewById(R.id.checkoutItemView)
        totalTxtView = findViewById(R.id.totalTxtView)
    }

    private fun setupRecyclerView() {
        val products = intent.getParcelableArrayListExtra<CheckoutProduct>("products") ?: emptyList()
        val adapter = CheckoutAdapter(products)

        checkoutItemView.layoutManager = LinearLayoutManager(this)
        checkoutItemView.adapter = adapter
    }

    fun calculateTotal() {
        val products = intent.getParcelableArrayListExtra<CheckoutProduct>("products")
        var total = 0.0
        products?.forEach { total += it.price * it.quantity }
        totalTxtView.text = "₱${"%.2f".format(total)}"
    }
}