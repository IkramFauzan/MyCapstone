package com.example.trycapstone.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.trycapstone.R
import com.example.trycapstone.data.News
import com.example.trycapstone.databinding.ActivityDetailBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        webView = binding.webView

        val url = intent.getStringExtra("URL_article")
        if (!url.isNullOrEmpty()) {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(url)
        } else {
            // Handle error case, URL is null or empty
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.progress = newProgress
                }
            }
        }


        setWebViewSettings()
        setContentView(binding.root)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webView.clearHistory()
        (webView.parent as? ViewGroup)?.removeView(webView)
        webView.destroy()
        super.onDestroy()
    }

    private fun setWebViewSettings() {
        webView.settings.apply {

            // Menyesuaikan konten WebView dengan lebar tata letak emulator
            loadWithOverviewMode = true
            useWideViewPort = true

            domStorageEnabled = true

            // Pengaturan lainnya sesuai kebutuhan Anda
            // ...

            // Contoh pengaturan cache
            cacheMode = WebSettings.LOAD_DEFAULT
        }
    }
}