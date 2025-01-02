package com.arch.domain.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.arch.common.ui.theme.AppTheme

@ExperimentalFoundationApi
@Composable
fun UrlText(
    modifier: Modifier = Modifier,
    url: String,
    onClick: (String) -> Unit,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = url,
        modifier = modifier.clickable { onClick(url) },
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        style = style,
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun LinkTextPreview() {
    AppTheme {
        UrlText(
            url = "https://www.github.com",
            onClick = {},
        )
    }
}