package com.linx.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.unit.dp
import com.linx.app.ui.components.MediaCard
import com.linx.app.ui.components.MusicRow
import com.linx.app.ui.components.SectionHeader
import com.linx.app.ui.theme.LinxColors
import com.linx.app.ui.theme.LinxDimens

private data class BannerItem(
    val title: String,
    val subtitle: String,
    val tag: String,
    val colors: List<Color>,
)

private val banners = listOf(
    BannerItem("今日编辑推荐", "把时间留给好故事", "推荐",
        listOf(Color(0xFF5B67F5), Color(0xFF3F47C9))),
    BannerItem("新歌首发", "本周热门抢先听", "新歌",
        listOf(Color(0xFFF05A7A), Color(0xFFC2355F))),
    BannerItem("漫画上新", "追更不迷路", "上新",
        listOf(Color(0xFF00B8A9), Color(0xFF007B74))),
    BannerItem("每日一句", "愿你今天也有好心情，配一张风景图", "每日一句",
        listOf(Color(0xFF8E6FE8), Color(0xFF5B3FA8))),
)

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = LinxDimens.S24),
    ) {
        item { HomeTopTitle() }
        item { BannerCarousel() }
        item { SearchBarBox() }
        item { QuickEntryRow() }
        item { WebSearchCard() }
        item { SectionHeader("编辑精选 · 小说", action = "更多") }
        item { HorizontalCardRow() }
        item { SectionHeader("为你推荐 · 音乐", action = "更多") }
        item { MusicListBlock() }
    }
}

@Composable
private fun HomeTopTitle() {
    Column(
        Modifier.padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S16),
    ) {
        Text("拾光", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(LinxDimens.S4))
        Text(
            "小说 · 音乐 · 广播剧 · 漫画，一处安放",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun BannerCarousel() {
    val pagerState = rememberPagerState(pageCount = { banners.size })
    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = LinxDimens.S16),
            pageSpacing = LinxDimens.S12,
        ) { page ->
            val b = banners[page]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(LinxDimens.R20))
                    .background(Brush.linearGradient(b.colors))
                    .clickable { /* 将来：跳转到对应内容或广告落地页 */ },
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = LinxDimens.S20),
                ) {
                    Text(b.title, color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(LinxDimens.S4))
                    Text(b.subtitle, color = Color.White.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodyMedium)
                }
                Text(
                    b.tag,
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(LinxDimens.S12)
                        .clip(RoundedCornerShape(LinxDimens.Full))
                        .background(Color.White.copy(alpha = 0.25f))
                        .padding(horizontal = LinxDimens.S10, vertical = 4.dp),
                )
            }
        }
        Spacer(Modifier.height(LinxDimens.S8))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(banners.size) { i ->
                val selected = pagerState.currentPage == i
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .height(6.dp)
                        .width(if (selected) 18.dp else 6.dp)
                        .clip(RoundedCornerShape(LinxDimens.Full))
                        .background(
                            if (selected) LinxColors.Brand
                            else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                        ),
                )
            }
        }
    }
}

@Composable
private fun SearchBarBox() {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S8)
            .clip(RoundedCornerShape(LinxDimens.Full))
            .background(scheme.surface)
            .clickable { /* 将来：打开搜索页 */ }
            .padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S12),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Search, contentDescription = null, tint = scheme.onSurfaceVariant)
        Spacer(Modifier.width(LinxDimens.S8))
        Text(
            "搜小说 / 音乐 / 广播剧 / 漫画",
            style = MaterialTheme.typography.bodyMedium,
            color = scheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
        )
        Box(
            Modifier
                .clip(RoundedCornerShape(LinxDimens.Full))
                .background(scheme.primary.copy(alpha = 0.12f))
                .padding(horizontal = LinxDimens.S12, vertical = 4.dp),
        ) { Text("全网搜", style = MaterialTheme.typography.labelSmall, color = scheme.primary) }
    }
}

private val quickEntries = listOf(
    Triple("小说", Icons.MenuBook, LinxColors.Brand),
    Triple("音乐", Icons.MusicNote, LinxColors.Accent),
    Triple("广播剧", Icons.Headphones, Color(0xFF00B8A9)),
    Triple("漫画", Icons.AutoStories, LinxColors.Gold),
)

@Composable
private fun QuickEntryRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S8),
        horizontalArrangement = Arrangement.spacedBy(LinxDimens.S12),
    ) {
        quickEntries.forEach { (label, icon, color) ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(LinxDimens.R16))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { /* 将来：进入对应分区 */ }
                    .padding(vertical = LinxDimens.S16),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(LinxDimens.R12))
                        .background(color.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.height(LinxDimens.S6))
                Text(label, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun WebSearchCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S8)
            .clip(RoundedCornerShape(LinxDimens.R16))
            .background(Brush.horizontalGradient(listOf(LinxColors.Brand, LinxColors.BrandDeep)))
            .clickable { /* 将来：打开内置浏览器搜索 */ }
            .padding(horizontal = LinxDimens.S16, vertical = LinxDimens.S14),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.TravelExplore, contentDescription = null, tint = Color.White)
        Spacer(Modifier.width(LinxDimens.S12))
        Column(Modifier.weight(1f)) {
            Text("站内找不到？去全网搜", color = Color.White,
                style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text("内置浏览器，搜到就能听能看", color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodySmall)
        }
        Icon(Icons.ChevronRight, contentDescription = null, tint = Color.White.copy(alpha = 0.8f))
    }
}

@Composable
private fun HorizontalCardRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = LinxDimens.S16),
        horizontalArrangement = Arrangement.spacedBy(LinxDimens.S12),
    ) {
        items(5) { i ->
            MediaCard(
                title = "小说名示例 $i",
                subtitle = "作者 · 都市",
                gradient = listOf(
                    LinxColors.Brand.copy(alpha = 1f - i * 0.06f),
                    LinxColors.BrandDeep,
                ),
                modifier = Modifier.width(120.dp),
            )
        }
    }
}

@Composable
private fun MusicListBlock() {
    Column(Modifier.padding(horizontal = LinxDimens.S16)) {
        repeat(3) { MusicRow(index = it) }
    }
}
