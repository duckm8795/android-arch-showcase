package com.arch.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arch.common.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity serves as the entry point of the application and hosts the main composable content.
 * It utilizes Hilt for dependency injection via the [@AndroidEntryPoint] annotation.
 *
 * Responsibilities:
 * - Sets up edge-to-edge display using [enableEdgeToEdge].
 * - Applies the application theme using [AppTheme].
 * - Hosts the navigation graph through [MainNavHost], which defines the app's navigation structure.
 *
 * Lifecycle:
 * - [onCreate] initializes the activity, sets up the UI content, and prepares the navigation host.
 */
@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainNavHost()
            }
        }
    }
}