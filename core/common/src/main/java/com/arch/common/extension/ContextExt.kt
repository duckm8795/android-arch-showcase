package com.arch.common.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Extension function for [Context] to open a URL in the default web browser.
 *
 * This function attempts to launch an [Intent] with the action [Intent.ACTION_VIEW] and the provided URL.
 * It wraps the operation in a [runCatching] block to safely handle potential exceptions (e.g., malformed URLs or
 * missing applications capable of handling the intent).
 *
 * @param url The URL to open in the browser.
 *
 * Example:
 * ```kotlin
 * val context: Context = ...
 * context.openBrowser("https://example.com")
 * ```
 *
 * Notes:
 * - Ensure the URL is properly formatted to avoid runtime errors.
 * - If no browser or appropriate handler exists on the device, the operation will fail silently.
 */
fun Context.openBrowser(url: String) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}