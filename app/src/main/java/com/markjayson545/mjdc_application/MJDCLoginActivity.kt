package com.markjayson545.mjdc_application

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MJDCLoginActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var attemptsTextView: TextView
    private lateinit var loginButton: Button
    private var attempts = 3
    private var minutes: Long = 15000
    var usernameValue = "admin"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mjdc_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getIds()
        attemptsTextView.text = "Attempts Left: $attempts"
        loginButton.setOnClickListener {
            if (username.text.toString() == usernameValue && password.text.toString() == "88888") {
                clearInputs()
                Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("username", usernameValue)
                startActivity(intent)
            } else {
                attempts--
                attemptsTextView.text = "Attempts Left: $attempts"
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }
            if (attempts == 0) {
                clearInputs()
                Toast.makeText(this, "No More attempts", Toast.LENGTH_SHORT).show()
                startTimer()
            }
        }
    }

    fun clearInputs(){
        username.text.clear()
        password.text.clear()
    }

    fun disableLoginAfterAttempts(){
        username.isEnabled = false
        password.isEnabled = false
        loginButton.isEnabled = false
        loginButton.text = "Try again after "
    }

    fun enableLoginAfterAttempts(){
        username.isEnabled = true
        password.isEnabled = true
        loginButton.isEnabled = true
        loginButton.text = "Login"
        attempts = 3
        attemptsTextView.text = "Attempts Left: $attempts"
    }

    fun startTimer(){
        attemptsTextView.text = "No more attempts"
        disableLoginAfterAttempts()
        val countDownTimer = object : CountDownTimer(minutes, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                loginButton.text = "Try again after " + millisUntilFinished / 1000
            }
            override fun onFinish() {
                enableLoginAfterAttempts()
                minutes = minutes * 2
                if (minutes == 60000.toLong()){
                    minutes = 15000
                }
            }
        }
        countDownTimer.start()
    }

    fun getIds(){
        username = findViewById<EditText>(R.id.username)
        password = findViewById<EditText>(R.id.password)
        attemptsTextView = findViewById<TextView>(R.id.attempts)
        loginButton = findViewById<Button>(R.id.loginBtn)
    }
}