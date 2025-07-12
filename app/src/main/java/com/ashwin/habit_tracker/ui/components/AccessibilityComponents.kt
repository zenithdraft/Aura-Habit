package com.ashwin.habit_tracker.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.semantics.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun AccessibleHabitCard(
    habit: com.ashwin.habit_tracker.data.Habit,
    isCompletedToday: Boolean = false,
    currentStreak: Int = 0,
    onCheckIn: () -> Unit = {},
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = false,
                onClick = onClick,
                role = Role.Button
            )
            .semantics {
                contentDescription = "Habit: ${habit.name}. " +
                        "Cue: ${habit.cue}. " +
                        "Routine: ${habit.routine}. " +
                        "Reward: ${habit.reward}. " +
                        if (isCompletedToday) "Completed today. " else "Not completed today. " +
                        "Current streak: $currentStreak days. " +
                        "Double tap to view details."
            },
        elevation = 4.dp,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Main Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Habit Name
                Text(
                    text = habit.name,
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Habit Loop Information
                Text(
                    text = "Cue: ${habit.cue}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
                
                Text(
                    text = "Routine: ${habit.routine}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
                
                Text(
                    text = "Reward: ${habit.reward}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Accessible Check-in Button
            AccessibleCheckInButton(
                isCompleted = isCompletedToday,
                currentStreak = currentStreak,
                habitName = habit.name,
                onCheckIn = onCheckIn
            )
        }
    }
}

@Composable
fun AccessibleCheckInButton(
    isCompleted: Boolean,
    currentStreak: Int,
    habitName: String,
    onCheckIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Check-in Button with accessibility
        FloatingActionButton(
            onClick = onCheckIn,
            backgroundColor = if (isCompleted) com.ashwin.habit_tracker.ui.theme.HabitGreen else MaterialTheme.colors.surface,
            contentColor = if (isCompleted) androidx.compose.ui.graphics.Color.White else MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(56.dp)
                .semantics {
                    contentDescription = if (isCompleted) {
                        "Mark $habitName as not completed for today. Currently completed."
                    } else {
                        "Mark $habitName as completed for today. Currently not completed."
                    }
                    role = Role.Button
                }
        ) {
            if (isCompleted) {
                Icon(
                    androidx.compose.material.icons.Icons.Default.Check,
                    contentDescription = null,
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
                        shape = androidx.compose.foundation.shape.CircleShape,
                        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.fillMaxSize()
                    ) {}
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Streak Counter with accessibility
        Text(
            text = if (currentStreak > 0) "$currentStreak day${if (currentStreak != 1) "s" else ""}" else "Start",
            fontSize = 12.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
            color = if (isCompleted) com.ashwin.habit_tracker.ui.theme.HabitGreen else MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.semantics {
                contentDescription = "Current streak: $currentStreak days"
            }
        )
    }
}

@Composable
fun AccessibleStatCard(
    title: String,
    value: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .semantics {
                contentDescription = "$title: $value. $description"
                role = Role.Image
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            
            Text(
                text = value,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
            
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun AccessibleProgressIndicator(
    progress: Float,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.semantics {
            contentDescription = "$label: ${(progress * 100).toInt()}% complete"
            role = Role.ProgressBar
        }
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
        )
    }
}

