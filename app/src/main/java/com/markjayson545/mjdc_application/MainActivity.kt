package com.markjayson545.mjdc_application

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    val calcAllPlaceholder = "Sum of ___ and ___ is: ___\n" +
            "Difference of ___ and ___ is: ___\n" +
            "Quotient of ___ and ___ is: ___\n" +
            "CheckoutProduct of ___ and ___ is: ___\n" +
            "Modulo of ___ and ___ is: ___\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var selectedOperation = ""

        findViewById<Button>(R.id.additionBtn).setOnClickListener {
            selectedOperation = "add"
            displayResult(selectedOperation)
            selectedOperation = ""
        }

        findViewById<Button>(R.id.subtractBtn).setOnClickListener {
            selectedOperation = "sub"
            displayResult(selectedOperation)
            selectedOperation = ""
        }

        findViewById<Button>(R.id.divideBtn).setOnClickListener {
            selectedOperation = "div"
            displayResult(selectedOperation)
            selectedOperation = ""
        }

        findViewById<Button>(R.id.multiplyBtn).setOnClickListener {
            selectedOperation = "mul"
            displayResult(selectedOperation)
            selectedOperation = ""
        }

        findViewById<Button>(R.id.moduloBtn).setOnClickListener {
            selectedOperation = "mod"
            displayResult(selectedOperation)
            selectedOperation = ""
        }

        findViewById<Button>(R.id.clearInputs).setOnClickListener {
            clearInputs()
        }

        findViewById<Button>(R.id.calculateAllBtn).setOnClickListener {
            calculateAll()
        }

        findViewById<TextView>(R.id.calcAllResult).text = calcAllPlaceholder

        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            finish()
        }
    }

    private lateinit var nextActivity: Button

    @SuppressLint("SetTextI18n")
    fun clearInputs() {
        val inp1 = findViewById<EditText>(R.id.inputNumber1)
        val inp2 = findViewById<EditText>(R.id.inputNumber2)

        val res1 = findViewById<TextView>(R.id.textView3)
        val res2 = findViewById<TextView>(R.id.calcAllResult)

        res1.text = "Result: "
        res2.text = calcAllPlaceholder



        inp1.text.clear()
        inp2.text.clear()
    }

    fun displayResult(operation: String) {

        val inp1 = findViewById<EditText>(R.id.inputNumber1)
        val inp2 = findViewById<EditText>(R.id.inputNumber2)

        if (operation == "") return

        val res = findViewById<TextView>(R.id.textView3)

        val num1 = inp1.text.toString().toDouble()
        val num2 = inp2.text.toString().toDouble()


        var result = 0.0

        when (operation) {
            "add" -> result = num1 + num2
            "sub" -> result = num1 - num2
            "mul" -> result = num1 * num2
            "div" -> result = num1 / num2
            "mod" -> result = num1 % num2
        }

        if (operation == "mod") {
            res.text = result.toInt().toString()
        } else {
            res.text = result.toString()
        }

    }


    fun calculateAll() {
        val resultTextView = findViewById<TextView>(R.id.calcAllResult)
        val inp1 = findViewById<EditText>(R.id.inputNumber1).text.toString()
        val inp2 = findViewById<EditText>(R.id.inputNumber2).text.toString()

        val result =
            "Sum of $inp1 and $inp2 is: ${inp1.toDouble() + inp2.toDouble()}\n" +
                    "Difference of $inp1 and $inp2 is: ${inp1.toDouble() - inp2.toDouble()}\n" +
                    "Quotient of $inp1 and $inp2 is: ${inp1.toDouble() / inp2.toDouble()}\n" +
                    "CheckoutProduct of $inp1 and $inp2 is: ${inp1.toDouble() * inp2.toDouble()}\n" +
                    "Modulo of $inp1 and $inp2 is: ${inp1.toInt() % inp2.toInt()}\n"

        resultTextView.text = result
    }


}