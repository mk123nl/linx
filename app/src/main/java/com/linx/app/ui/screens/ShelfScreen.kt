package com.linx.app.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.runtime.Composable
import com.linx.app.ui.components.EmptyState

@Composable
fun ShelfScreen() {
    EmptyState(
        icon = Icons.Filled.CollectionsBookmark,
        title = "书架还是空的",
        subtitle = "收藏的小说、音乐、广播剧和漫画都会出现在这里",
        actionLabel = "去逛逛",
        onClick = {},
    )
}
