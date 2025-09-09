package com.markjayson545.mjdc_application

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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

        findViewById<RadioGroup>(R.id.operationRadioGroup).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.additionRadio -> selectedOperation = "addition"
                R.id.subtractionRadio -> selectedOperation = "subtraction"
                R.id.multiplicationRadio -> selectedOperation = "multiplication"
                R.id.divisionRadio -> selectedOperation = "division"
            }
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            if (selectedOperation != ""){
                displayResult(selectedOperation)
            } else {
                Toast.makeText(this, "Please select an operation", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun displayResult(operation: String) {
        val inp1 = findViewById<EditText>(R.id.inputNumber1)
        val inp2 = findViewById<EditText>(R.id.inputNumber2)

        val res = findViewById<TextView>(R.id.textView3)

        val num1 = inp1.text.toString().toInt()
        val num2 = inp2.text.toString().toInt()

        var sum = 0

        when (operation) {
            "addition" -> sum = num1 + num2
            "subtraction" -> sum = num1 - num2
            "multiplication" -> sum = num1 * num2
            "division" -> sum = num1 / num2
        }

        res.text = sum.toString()

    }


}