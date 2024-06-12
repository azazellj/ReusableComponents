package com.azazellj.compose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.azazellj.compose.R

@Composable
fun ContactViewV1(
    imagePainter: Painter,
    managerPainter: Painter?,
    title: String?,
    fullName: String,
    modifiedTime: String?,
    modifier: Modifier = Modifier,
    onChatClicked: (() -> Unit)? = null,
    onMainClicked: (() -> Unit)? = null,
    onCallClicked: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .sizeIn(
                minHeight = when {
                    title.isNullOrBlank() && modifiedTime.isNullOrBlank() -> 56.dp
                    !title.isNullOrBlank() -> 72.dp
                    else -> 88.dp
                },
            )
            .padding(
                horizontal = 16.dp,
                vertical = when {
                    title.isNullOrBlank() && modifiedTime.isNullOrBlank() -> 8.dp
                    else -> 16.dp
                },
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
                .clip(CircleShape)
                .align(
                    alignment = when {
                        !title.isNullOrBlank() && !modifiedTime.isNullOrBlank() -> Alignment.Top
                        else -> Alignment.CenterVertically
                    },
                ),
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
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
                if (managerPainter != null) {
                    Image(
                        painter = managerPainter,
                        contentDescription = "manager image",
                        modifier = Modifier
                            .requiredSize(16.dp)
                            .clip(CircleShape),
                    )
                }
                if (!modifiedTime.isNullOrBlank()) {
                    Text(
                        text = modifiedTime,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            if (onChatClicked != null) {
                IconButton(
                    onClick = onChatClicked,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Message,
                        contentDescription = "chat action",
                    )
                }
            }
            if (onMainClicked != null) {
                IconButton(
                    onClick = onMainClicked,
                ) {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = "mail action",
                    )
                }
            }
            if (onCallClicked != null) {
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
}

@Composable
fun FullNameContactView(
    imagePainter: Painter,
    fullName: String,
    modifier: Modifier = Modifier,
) {
    ContactView(
        leadingPainter = imagePainter,
        title = fullName,
        modifier = modifier,
    )
}

@Composable
fun ActionsContactView(
    imagePainter: Painter,
    fullName: String,
    modifiedTime: String,
    modifier: Modifier = Modifier,
    onChatClicked: (() -> Unit)? = null,
    onMainClicked: (() -> Unit)? = null,
    onCallClicked: (() -> Unit)? = null,
) {
    ContactView(
        leadingPainter = imagePainter,
        title = fullName,
        modifier = modifier,
        config = ContactViewDefaults.config(
            minHeight = 72.dp,
            verticalPadding = 16.dp,
        ),
        content = {
            ContactContentView(
                title = fullName,
                subtitle = modifiedTime,
            )
        },
        trailingView = {
            ContactActionsView(
                onChatClicked = onChatClicked,
                onMainClicked = onMainClicked,
                onCallClicked = onCallClicked,
            )
        },
    )
}

@Composable
fun DetailsContactView(
    imagePainter: Painter,
    managerPainter: Painter,
    title: String,
    fullName: String,
    modifiedTime: String,
    modifier: Modifier = Modifier,
) {
    ContactView(
        leadingPainter = imagePainter,
        title = fullName,
        modifier = modifier,
        config = ContactViewDefaults.config(
            minHeight = 88.dp,
            verticalPadding = 16.dp,
            headerAlignment = Alignment.Top,
        ),
        content = {
            ContactContentView(
                title = fullName,
                header = title,
                subtitle = modifiedTime,
                managerPainter = managerPainter,
            )
        },
    )
}

@Composable
private fun ContactView(
    leadingPainter: Painter,
    title: String,
    modifier: Modifier = Modifier,
    config: ContactViewConfig = ContactViewDefaults.config(),
    leadingView: @Composable RowScope.() -> Unit = {
        ContactLeadingView(
            imagePainter = leadingPainter,
            headerAlignment = config.headerAlignment,
        )
    },
    content: @Composable ColumnScope.() -> Unit = {
        ContactContentView(
            title = title,
        )
    },
    trailingView: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .sizeIn(
                minHeight = config.minHeight,
            )
            .padding(
                horizontal = 16.dp,
                vertical = config.verticalPadding,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
        ),
    ) {
        leadingView.invoke(this)

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically),
        ) {
            content.invoke(this)
        }

        trailingView?.invoke(this)
    }
}

@Composable
fun RowScope.ContactLeadingView(
    imagePainter: Painter,
    headerAlignment: Alignment.Vertical,
) {
    Image(
        painter = imagePainter,
        contentDescription = "contact image",
        modifier = Modifier
            .requiredSize(40.dp)
            .clip(CircleShape)
            .align(
                alignment = headerAlignment,
            ),
    )
}

@Composable
fun ColumnScope.ContactContentView(
    title: String,
    header: String? = null,
    subtitle: String? = null,
    managerPainter: Painter? = null,
) {
    if (!header.isNullOrBlank()) {
        Text(
            text = header,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall,
        )
    }

    Text(
        text = title,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyMedium,
    )

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
        ),
    ) {
        if (managerPainter != null) {
            Image(
                painter = managerPainter,
                contentDescription = "manager image",
                modifier = Modifier
                    .requiredSize(16.dp)
                    .clip(CircleShape),
            )
        }
        if (!subtitle.isNullOrBlank()) {
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
fun RowScope.ContactActionsView(
    onChatClicked: (() -> Unit)? = null,
    onMainClicked: (() -> Unit)? = null,
    onCallClicked: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .align(Alignment.CenterVertically),
    ) {
        if (onChatClicked != null) {
            IconButton(
                onClick = onChatClicked,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Message,
                    contentDescription = "chat action",
                )
            }
        }
        if (onMainClicked != null) {
            IconButton(
                onClick = onMainClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "mail action",
                )
            }
        }
        if (onCallClicked != null) {
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

// do not do so
@Immutable
class ContactViewConfig internal constructor(
    internal val minHeight: Dp,
    internal val verticalPadding: Dp,
    internal val headerAlignment: Alignment.Vertical,
)

object ContactViewDefaults {

    @Composable
    fun config(
        minHeight: Dp = 56.dp,
        verticalPadding: Dp = 8.dp,
        headerAlignment: Alignment.Vertical = Alignment.CenterVertically,
    ): ContactViewConfig = ContactViewConfig(
        minHeight = minHeight,
        verticalPadding = verticalPadding,
        headerAlignment = headerAlignment,
    )
}

@Preview
@Composable
private fun ContactViewV1Preview() {
    Column {
        ContactFullNameV2ViewPreview()
        HorizontalDivider()
        ContactViewV1(
            imagePainter = when {
                LocalInspectionMode.current -> painterResource(id = R.drawable.eleanor_pena)
                else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
            },
            managerPainter = null,
            title = null,
            fullName = "Eleanor Pena",
            modifiedTime = null,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
        )
        HorizontalDivider()
        FullNameContactView(
            imagePainter = when {
                LocalInspectionMode.current -> painterResource(id = R.drawable.eleanor_pena)
                else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
            },
            fullName = "Eleanor Pena",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
        )
        HorizontalDivider()
        ContactActionsV1ViewPreview()
        HorizontalDivider()
        ContactViewV1(
            imagePainter = when {
                LocalInspectionMode.current -> painterResource(id = R.drawable.cameron_williamson)
                else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
            },
            managerPainter = null,
            title = null,
            fullName = "Cameron Williamson",
            modifiedTime = "Last edited 1 week ago",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            onChatClicked = {},
            onMainClicked = {},
            onCallClicked = {},
        )
        HorizontalDivider()
        ActionsContactView(
            imagePainter = when {
                LocalInspectionMode.current -> painterResource(id = R.drawable.cameron_williamson)
                else -> rememberAsyncImagePainter(model = "https://images.stockcake.com/public/1/b/e/1be26278-b679-47a6-b17a-f3a66bc3db92_large/elegant-senior-portrait-stockcake.jpg")
            },
            fullName = "Cameron Williamson",
            modifiedTime = "Last edited 1 week ago",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            onChatClicked = {},
            onMainClicked = {},
            onCallClicked = {},
        )
        HorizontalDivider()
        ContactDetailsV1ViewPreview()
        HorizontalDivider()
        ContactViewV1(
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
        HorizontalDivider()
        DetailsContactView(
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
}

