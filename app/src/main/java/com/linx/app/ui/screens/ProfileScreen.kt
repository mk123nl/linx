package com.linx.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.linx.app.ui.theme.LinxColors
import com.linx.app.ui.theme.LinxDimens

private data class SettingItem(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val color: Color,
)

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val scheme = MaterialTheme.colorScheme

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(LinxDimens.S16),
        verticalArrangement = Arrangement.spacedBy(LinxDimens.S16),
    ) {
        item {
            Column {
                Text("我的", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Black)
                Spacer(Modifier.height(LinxDimens.S4))
                Text("账号与设置", style = MaterialTheme.typography.bodyMedium, color = scheme.onSurfaceVariant)
            }
        }
        item {
            SettingCard(listOf(
                SettingItem(Icons.Filled.Palette, "外观主题", "多套主题色 · 浅色/深色", LinxColors.Brand),
                SettingItem(Icons.Filled.Wallpaper, "背景图", "从相册选择你的背景", Color(0xFF00B8A9)),
                SettingItem(Icons.Filled.Speed, "性能模式", "画质优先 / 流畅优先", LinxColors.Gold),
            ), onItemClick = { title ->
                Toast.makeText(context, "「$title」功能开发中，先看UI", Toast.LENGTH_SHORT).show()
            })
        }
        item {
            SettingCard(listOf(
                SettingItem(Icons.Filled.Group, "官方QQ频道", "加入粉丝群聊", LinxColors.Accent),
                SettingItem(Icons.Filled.Feedback, "意见反馈", "跟我说说你的想法", LinxColors.Brand),
            ), onItemClick = { title ->
                Toast.makeText(context, "「$title」稍后接入", Toast.LENGTH_SHORT).show()
            })
        }
        item {
            SettingCard(listOf(
                SettingItem(Icons.Filled.Info, "关于", "拾光 v0.1.0", LinxColors.InkSubLight),
            ), onItemClick = {})
        }
    }
}

@Composable
private fun SettingCard(items: List<SettingItem>, onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(LinxDimens.R20))
            .background(MaterialTheme.colorScheme.surface),
    ) {
        items.forEachIndexed { index, item ->
            SettingRow(item = item, onClick = { onItemClick(item.title) })
            if (index != items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(start = 68.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.15f),
                )
            }
        }
    }
}

@Composable
private fun SettingRow(item: SettingItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S12),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(LinxDimens.R12))
                .background(item.color.copy(alpha = 0.14f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(item.icon, contentDescription = null, tint = item.color, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(LinxDimens.S12))
        Column(Modifier.weight(1f)) {
            Text(item.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
            Text(item.subtitle, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(Icons.Filled.ChevronRight, contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
