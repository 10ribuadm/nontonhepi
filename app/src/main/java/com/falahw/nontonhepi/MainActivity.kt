package com.falahw.nontonhepi

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.falahw.nontonhepi.ui.theme.NontonhepiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install Splash Screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NontonhepiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RewardWebView(
                        url = "https://10ribuadm.github.io/reward-web/",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RewardWebView(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                
                // Konfigurasi Penting agar Firebase & JS berjalan lancar
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient() // Dibutuhkan untuk Alert/Popup JS
                
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true // PENTING: Agar ID User tersimpan di HP
                    databaseEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    cacheMode = WebSettings.LOAD_DEFAULT
                }
                
                loadUrl(url)
            }
        },
        update = { webView ->
            // Update URL jika diperlukan
        }
    )
}
