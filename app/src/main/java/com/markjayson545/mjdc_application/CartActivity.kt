package com.markjayson545.mjdc_application

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckoutProduct(
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageResId: Int) : Parcelable

class CartActivity : AppCompatActivity() {

    private lateinit var logoutBtn: Button
    private lateinit var welcomeTxt: TextView

    private lateinit var incrementBtn1: Button
    private lateinit var decrementBtn1: Button

    private lateinit var incrementBtn2: Button
    private lateinit var decrementBtn2: Button

    private lateinit var nxtBtnOne: Button
    private lateinit var nxtBtnTwo: Button

    private lateinit var quantity1: TextView
    private lateinit var quantity2: TextView

    private lateinit var productImg1: ImageView
    private lateinit var productImg2: ImageView

    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox

    private lateinit var checkoutBtn: Button

    var currentQuantity1 = 1
    var currentQuantity2 = 1

    var currentPrice1 = 100.0
    var currentPrice2 = 100.0


    val product1 = listOf(
        R.mipmap.kb1_1_foreground,
        R.mipmap.kb1_2_foreground,
        R.mipmap.kb1_3_foreground,
        R.mipmap.kb1_4_foreground
    )
    val product2 = listOf(
        R.mipmap.ms1_1_foreground,
        R.mipmap.ms1_2_foreground,
        R.mipmap.ms1_3_foreground,
        R.mipmap.ms1_4_foreground
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var currentImage1 = 1
        var currentImage2 = 1

        initializeIds()
        nxtBtnOne.setOnClickListener {
            productImg1.setImageResource(product1[currentImage1])
            currentImage1 = (currentImage1 + 1) % product1.size
        }
        nxtBtnTwo.setOnClickListener {
            productImg2.setImageResource(product2[currentImage2])
            currentImage2 = (currentImage2 + 1) % product2.size
        }
    }

    fun checkout(
    ) {
        val intent = Intent(this, CheckoutActivity::class.java)
        val checkoutProducts = mutableListOf<CheckoutProduct>()
        if (checkBox1.isChecked){
            checkoutProducts += CheckoutProduct("G915 X", currentPrice1, currentQuantity1, product1[0])
        }
        if (checkBox2.isChecked){
            checkoutProducts += CheckoutProduct("Pro 2 Lightspeed", currentPrice2, currentQuantity2, product2[0])
        }
        if (checkoutProducts.isEmpty()) {
            return
        }
        intent.putExtra("products", ArrayList(checkoutProducts))
        startActivity(intent)
    }


    fun initializeIds() {
        logoutBtn = findViewById(R.id.logoutBtnCart)
        logoutBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to logout?")
            builder.setNegativeButton("No") { _, _ -> }
            builder.setPositiveButton("Yes") { _, _ ->
                finish()
            }
            builder.show()
        }
        welcomeTxt = findViewById(R.id.userLoggedIn)
        welcomeTxt.text = "Welcome, " + intent.getStringExtra("username")

        incrementBtn1 = findViewById(R.id.incrementBtn)
        incrementBtn1.setOnClickListener {
            currentQuantity1++
            if (currentQuantity1 > 1) {
                decrementBtn1.isEnabled = true
                quantity1.text = currentQuantity1.toString()
            }
        }
        decrementBtn1 = findViewById(R.id.decrementBtn)
        decrementBtn1.setOnClickListener {
            currentQuantity1--
            if (currentQuantity1 == 1) {
                decrementBtn1.isEnabled = false
            }
            quantity1.text = currentQuantity1.toString()
        }

        incrementBtn2 = findViewById(R.id.incrementBtn2)
        incrementBtn2.setOnClickListener {
            currentQuantity2++
            if (currentQuantity2 > 1) {
                decrementBtn2.isEnabled = true
                quantity2.text = currentQuantity2.toString()
            }
        }
        decrementBtn2 = findViewById(R.id.decrementBtn2)
        decrementBtn2.setOnClickListener {
            currentQuantity2--
            if (currentQuantity2 == 1) {
                decrementBtn2.isEnabled = false
            }
            quantity2.text = currentQuantity2.toString()
        }

        nxtBtnOne = findViewById(R.id.nxtBtnOne)
        nxtBtnTwo = findViewById(R.id.nxtBtnTwo)

        quantity1 = findViewById(R.id.quantity1)
        quantity2 = findViewById(R.id.quantity2)

        productImg1 = findViewById(R.id.productImg1)
        productImg2 = findViewById(R.id.productImg2)
        productImg1.setImageResource(product1[0])
        productImg2.setImageResource(product2[0])
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkoutBtn = findViewById(R.id.checkoutBtn)
        checkoutBtn.setOnClickListener {
            checkout()
        }
    }
}