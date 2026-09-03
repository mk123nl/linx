package com.linx.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.linx.app.ui.screens.ExploreScreen
import com.linx.app.ui.screens.HomeScreen
import com.linx.app.ui.screens.ProfileScreen
import com.linx.app.ui.screens.ShelfScreen
import com.linx.app.ui.theme.LinxColors

private data class TabItem(val label: String, val icon: ImageVector)

private val tabs = listOf(
    TabItem("首页", Icons.Home),
    TabItem("发现", Icons.Explore),
    TabItem("书架", Icons.CollectionsBookmark),
    TabItem("我的", Icons.Person),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selected by remember { mutableIntStateOf(0) }
    val isDark = isSystemInDarkTheme()

    // 渐变背景：浅色是淡紫雾，深色是星空紫
    val bgColors = if (isDark) {
        listOf(Color(0xFF141226), Color(0xFF241B4E), Color(0xFF0F1017))
    } else {
        listOf(Color(0xFFE8EAFF), Color(0xFFDCD3FF), Color(0xFFF5F6FB))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(bgColors)),
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                ) {
                    tabs.forEachIndexed { i, tab ->
                        NavigationBarItem(
                            selected = selected == i,
                            onClick = { selected = i },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = LinxColors.Brand,
                                selectedTextColor = LinxColors.Brand,
                                indicatorColor = LinxColors.Brand.copy(alpha = 0.14f),
                            ),
                        )
                    }
                }
            },
        ) { padding ->
            Box(Modifier.padding(padding)) {
                when (selected) {
                    0 -> HomeScreen()
                    1 -> ExploreScreen()
                    2 -> ShelfScreen()
                    3 -> ProfileScreen()
                }
            }
        }
    }
}
