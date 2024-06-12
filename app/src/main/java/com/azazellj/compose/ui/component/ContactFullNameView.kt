package com.azazellj.compose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.azazellj.compose.R
import com.azazellj.compose.ui.theme.ReusableComponentsTheme

@Composable
fun ContactFullNameV1View(
    imageUrl: String,
    fullName: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .sizeIn(
                minHeight = 56.dp,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
        ),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "contact image",
            modifier = Modifier
                .requiredSize(40.dp),
        )

        Text(
            text = fullName,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun ContactFullNameV1ViewPreview() = ReusableComponentsTheme {
    ContactFullNameV1View(
        imageUrl = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg",
        fullName = "Eleanor Pena",
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    )
}

@Composable
fun ContactFullNameV2View(
    imagePainter: Painter,
    fullName: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .sizeIn(
                minHeight = 56.dp,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "contact image",
            modifier = Modifier
                .requiredSize(40.dp)
                .clip(CircleShape),
        )

        Text(
            text = fullName,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun ContactFullNameV2ViewPreview() = ReusableComponentsTheme {
    ContactFullNameV2View(
        imagePainter = when {
            LocalInspectionMode.current -> painterResource(id = R.drawable.eleanor_pena)
            else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
        },
        fullName = "Eleanor Pena",
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    )
}
