package com.azazellj.compose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun ContactDetailsV1View(
    imagePainter: Painter,
    managerPainter: Painter,
    title: String,
    fullName: String,
    modifiedTime: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .sizeIn(
                minHeight = 88.dp,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
        ),
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "contact image",
            modifier = Modifier
                .requiredSize(40.dp)
                .clip(CircleShape),
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = fullName,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                ),
            ) {
                Image(
                    painter = managerPainter,
                    contentDescription = "manager image",
                    modifier = Modifier
                        .requiredSize(16.dp)
                        .clip(CircleShape),
                )
                Text(
                    text = modifiedTime,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun ContactDetailsV1ViewPreview() = ReusableComponentsTheme {
    ContactDetailsV1View(
        imagePainter = when {
            LocalInspectionMode.current -> painterResource(id = R.drawable.rose_walden)
            else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
        },
        managerPainter = when {
            LocalInspectionMode.current -> painterResource(id = R.drawable.dianne_russell)
            else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
        },
        title = "Lead",
        fullName = "Rose Walden",
        modifiedTime = "Last edited 2 hours ago",
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    )
}
