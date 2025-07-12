package com.ashwin.habit_tracker.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.ui.theme.HabitGreen

@Composable
fun CheckInButton(
    isCompleted: Boolean,
    currentStreak: Int,
    onCheckIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Check-in Button
        FloatingActionButton(
            onClick = onCheckIn,
            backgroundColor = if (isCompleted) HabitGreen else MaterialTheme.colors.surface,
            contentColor = if (isCompleted) Color.White else MaterialTheme.colors.onSurface,
            modifier = Modifier.size(56.dp)
        ) {
            if (isCompleted) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Completed",
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        shape = CircleShape,
                        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.fillMaxSize()
                    ) {}
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Streak Counter
        Text(
            text = if (currentStreak > 0) "$currentStreak day${if (currentStreak != 1) "s" else ""}" else "Start",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isCompleted) HabitGreen else MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
        )
    }
}

