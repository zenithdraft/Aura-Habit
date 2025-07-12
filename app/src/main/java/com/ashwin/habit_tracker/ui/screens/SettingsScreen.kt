package com.ashwin.habit_tracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("Settings") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SettingsSection(title = "Appearance") {
                    SettingsItem(
                        icon = Icons.Default.Palette,
                        title = "Theme",
                        subtitle = "Light, Dark, or System",
                        onClick = { /* Handle theme change */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.ColorLens,
                        title = "Accent Color",
                        subtitle = "Customize app colors",
                        onClick = { /* Handle color change */ }
                    )
                }
            }
            
            item {
                SettingsSection(title = "Notifications") {
                    var notificationsEnabled by remember { mutableStateOf(true) }
                    
                    SettingsToggleItem(
                        icon = Icons.Default.Notifications,
                        title = "Enable Notifications",
                        subtitle = "Receive habit reminders",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Schedule,
                        title = "Notification Time",
                        subtitle = "Default reminder time",
                        onClick = { /* Handle time picker */ }
                    )
                }
            }
            
            item {
                SettingsSection(title = "Data") {
                    SettingsItem(
                        icon = Icons.Default.Backup,
                        title = "Export Data",
                        subtitle = "Backup your habits",
                        onClick = { /* Handle export */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Restore,
                        title = "Import Data",
                        subtitle = "Restore from backup",
                        onClick = { /* Handle import */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Delete,
                        title = "Clear All Data",
                        subtitle = "Reset the app",
                        onClick = { /* Handle clear data */ },
                        isDestructive = true
                    )
                }
            }
            
            item {
                SettingsSection(title = "About") {
                    SettingsItem(
                        icon = Icons.Default.Info,
                        title = "App Version",
                        subtitle = "1.0.0",
                        onClick = { }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Help,
                        title = "Help & Support",
                        subtitle = "Get help with the app",
                        onClick = { /* Handle help */ }
                    )
                    
                    SettingsItem(
                        icon = Icons.Default.Star,
                        title = "Rate the App",
                        subtitle = "Leave a review",
                        onClick = { /* Handle rating */ }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 2.dp
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isDestructive) MaterialTheme.colors.error else MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDestructive) MaterialTheme.colors.error else MaterialTheme.colors.onSurface
            )
            
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
        }
        
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun SettingsToggleItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
            
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary
            )
        )
    }
}

