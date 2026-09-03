package com.linx.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.linx.app.ui.theme.LinxColors
import com.linx.app.ui.theme.LinxDimens

@Composable
fun ExploreScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(LinxDimens.S16),
        horizontalArrangement = Arrangement.spacedBy(LinxDimens.S12),
        verticalArrangement = Arrangement.spacedBy(LinxDimens.S12),
    ) {
        item(span = { GridItemSpan(3) }) {
            Text("发现", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Black)
        }
        item(span = { GridItemSpan(3) }) {
            Text("各分区推荐位", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        items(12) { i ->
            Box(
                modifier = Modifier
                    .aspectRatio(0.85f)
                    .clip(RoundedCornerShape(LinxDimens.R16))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                LinxColors.Brand.copy(alpha = 1f - (i % 4) * 0.12f),
                                LinxColors.BrandDeep,
                            )
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Filled.AutoStories, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.height(LinxDimens.S8))
                    Text("内容位", color = Color.White, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
