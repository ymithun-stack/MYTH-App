package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SecondaryCyan
import com.example.ui.viewmodel.AppTab

@Composable
fun KineticBottomBar(
  currentTab: AppTab,
  onTabSelected: (AppTab) -> Unit,
  modifier: Modifier = Modifier
) {
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
    contentColor = MaterialTheme.colorScheme.onSurface,
    tonalElevation = 8.dp,
    modifier = modifier
      .fillMaxWidth()
      .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
      .navigationBarsPadding()
  ) {
    AppTab.values().forEach { tab ->
      val selected = currentTab == tab
      val icon: ImageVector = when (tab) {
        AppTab.HOME -> Icons.Default.Home
        AppTab.SIMULATOR -> Icons.Default.PlayCircle
        AppTab.EXPLORER -> Icons.Default.Hub
        AppTab.INDUSTRIES -> Icons.Default.Apartment
        AppTab.AUDIT -> Icons.Default.FactCheck
      }

      NavigationBarItem(
        selected = selected,
        onClick = { onTabSelected(tab) },
        icon = {
          Icon(
            imageVector = icon,
            contentDescription = tab.title,
            modifier = Modifier.size(22.dp)
          )
        },
        label = {
          Text(
            text = tab.title,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 10.sp,
              fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
          selectedTextColor = SecondaryCyan,
          indicatorColor = MaterialTheme.colorScheme.primaryContainer,
          unselectedIconColor = MaterialTheme.colorScheme.outline,
          unselectedTextColor = MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier.testTag("tab_${tab.name.lowercase()}")
      )
    }
  }
}
