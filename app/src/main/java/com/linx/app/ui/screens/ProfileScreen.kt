package com.linx.app.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import com.linx.app.ui.components.EmptyState

@Composable
fun ProfileScreen() {
    EmptyState(
        icon = Icons.Filled.Person,
        title = "登录后体验完整功能",
        subtitle = "进度云同步 · 社区互动 · 下载离线\n（账号系统接入中）",
        actionLabel = "登录",
        onClick = {},
    )
}
