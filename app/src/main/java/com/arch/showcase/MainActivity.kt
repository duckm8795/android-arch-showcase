package com.arch.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arch.showcase.nav.MainNavHost
import com.arch.showcase.ui.theme.AndroidArchShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidArchShowcaseTheme {
                MainNavHost()
            }
        }
    }
}