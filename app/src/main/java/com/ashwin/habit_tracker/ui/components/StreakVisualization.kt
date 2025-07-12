package com.ashwin.habit_tracker.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.ui.theme.HabitGreen
import com.ashwin.habit_tracker.ui.theme.HabitOrange

@Composable
fun StreakVisualization(
    currentStreak: Int,
    longestStreak: Int,
    streakHistory: List<Int>, // Last 30 days of streak values
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Streak Progress",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Current vs Longest Streak
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Current Streak
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Current",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "$currentStreak",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = HabitGreen
                    )
                    Text(
                        text = "days",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
                
                // Divider
                Divider(
                    modifier = Modifier
                        .height(60.dp)
                        .width(1.dp),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                )
                
                // Longest Streak
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Best",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "$longestStreak",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = HabitOrange
                    )
                    Text(
                        text = "days",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Streak History Chart
            if (streakHistory.isNotEmpty()) {
                Text(
                    text = "Last 30 Days",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    drawStreakChart(streakHistory, longestStreak)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Progress message
            val progressMessage = when {
                currentStreak == 0 -> "Start your streak today! 🚀"
                currentStreak < 7 -> "Great start! Keep going! 💪"
                currentStreak < 21 -> "You're building momentum! 🔥"
                currentStreak < 66 -> "Habit forming in progress! ⭐"
                else -> "Habit mastery achieved! 🏆"
            }
            
            Text(
                text = progressMessage,
                fontSize = 14.sp,
                color = HabitGreen,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private fun DrawScope.drawStreakChart(streakHistory: List<Int>, maxStreak: Int) {
    if (streakHistory.isEmpty()) return
    
    val chartWidth = size.width
    val chartHeight = size.height
    val barWidth = chartWidth / streakHistory.size
    val maxValue = maxOf(maxStreak, streakHistory.maxOrNull() ?: 1)
    
    streakHistory.forEachIndexed { index, streak ->
        val barHeight = if (maxValue > 0) (streak.toFloat() / maxValue) * chartHeight else 0f
        val x = index * barWidth
        val y = chartHeight - barHeight
        
        val color = when {
            streak == 0 -> Color.Gray.copy(alpha = 0.3f)
            streak < 7 -> HabitGreen.copy(alpha = 0.6f)
            else -> HabitGreen
        }
        
        drawRect(
            color = color,
            topLeft = Offset(x + barWidth * 0.1f, y),
            size = Size(barWidth * 0.8f, barHeight)
        )
    }
}

