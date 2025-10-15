package com.markjayson545.mjdc_application

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebViewActivity : AppCompatActivity() {
    lateinit var urlField: EditText
    lateinit var webview: WebView
    lateinit var goBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initIds()
        goBtn.setOnClickListener {
            webview.loadUrl(urlField.text.toString())
        }
    }
    fun initIds(){
        goBtn = findViewById(R.id.goBtn)
        urlField = findViewById(R.id.urlField)
        webview = findViewById(R.id.webview)
        webview.webViewClient = MyWebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.loadUrl("https://www.google.com/")
    }
}

class MyWebViewClient : WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}