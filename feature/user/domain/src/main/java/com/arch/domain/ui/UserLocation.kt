package com.arch.domain.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arch.common.ui.theme.AppTheme
import com.arch.domain.R

@Composable
internal fun UserLocation(
    location: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_location),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )
        Text(
            text = location,
            modifier = Modifier.padding(start = 2.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserCountryPreview() {
    AppTheme {
        UserLocation(
            location = "Ho Chi Minh City, \nVietnam",
            modifier = Modifier.width(200.dp),
        )
    }
}