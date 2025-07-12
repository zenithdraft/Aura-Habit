package com.ashwin.habit_tracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.data.Habit
import com.ashwin.habit_tracker.ui.theme.HabitGreen

@Composable
fun HabitCardUpdated(
    habit: Habit,
    isCompletedToday: Boolean = false,
    currentStreak: Int = 0,
    onCheckIn: () -> Unit = {},
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
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
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Cue → Routine → Reward
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cue: ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = habit.cue,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Routine: ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = habit.routine,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Reward: ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = habit.reward,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Frequency and Reminder
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Chip(
                        onClick = { },
                        colors = ChipDefaults.chipColors(
                            backgroundColor = HabitGreen.copy(alpha = 0.1f),
                            contentColor = HabitGreen
                        )
                    ) {
                        Text(
                            text = habit.frequency,
                            fontSize = 12.sp
                        )
                    }
                    
                    if (habit.reminderTime != null) {
                        Text(
                            text = "⏰ ${habit.reminderTime}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Check-in Button
            CheckInButton(
                isCompleted = isCompletedToday,
                currentStreak = currentStreak,
                onCheckIn = onCheckIn
            )
        }
    }
}

