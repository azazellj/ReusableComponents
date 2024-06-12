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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ContactActionsV1View(
    imagePainter: Painter,
    fullName: String,
    modifiedTime: String,
    modifier: Modifier = Modifier,
    onChatClicked: () -> Unit = {},
    onMainClicked: () -> Unit = {},
    onCallClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .sizeIn(
                minHeight = 72.dp,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp,
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

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = fullName,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = modifiedTime,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            IconButton(
                onClick = onChatClicked,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Message,
                    contentDescription = "chat action",
                )
            }
            IconButton(
                onClick = onMainClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "mail action",
                )
            }
            IconButton(
                onClick = onCallClicked,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "call action",
                )
            }
        }
    }
}

@Preview
@Composable
fun ContactActionsV1ViewPreview() = ReusableComponentsTheme {
    ContactActionsV1View(
        imagePainter = when {
            LocalInspectionMode.current -> painterResource(id = R.drawable.cameron_williamson)
            else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
        },
        fullName = "Cameron Williamson",
        modifiedTime = "Last edited 1 week ago",
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    )
}
