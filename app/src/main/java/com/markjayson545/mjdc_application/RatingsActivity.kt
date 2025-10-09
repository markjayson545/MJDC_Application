package com.markjayson545.mjdc_application

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RatingsActivity : AppCompatActivity() {
    private lateinit var logoutBtn: Button
    private lateinit var imageView: ImageView
    private lateinit var ratingText: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var nextBtn: Button

    private val images = listOf(
        R.mipmap.kb1_foreground,
        R.mipmap.kb2_foreground,
        R.mipmap.kb3_foreground,
        R.mipmap.kb4_foreground
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ratings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeIds()
        logoutBtn.setOnClickListener {
            finish()
        }
        var currentImage = 0
        nextBtn.setOnClickListener {
            imageView.setImageResource(images[currentImage])
            currentImage = (currentImage + 1) % images.size
        }
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            ratingText.text = "Rating: $rating"
        }
    }

    fun initializeIds() {
        logoutBtn = findViewById(R.id.logoutBtnRatings)
        imageView = findViewById(R.id.productImage)
        ratingText = findViewById(R.id.ratingText)
        ratingBar = findViewById(R.id.ratingBar)
        nextBtn = findViewById(R.id.nextBtn)
    }

}