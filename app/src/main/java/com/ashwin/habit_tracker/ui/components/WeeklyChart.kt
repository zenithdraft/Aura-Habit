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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.ui.theme.HabitGreen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeeklyChart(
    completionData: List<Boolean>, // 7 days of completion data (true/false)
    modifier: Modifier = Modifier
) {
    val dayLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "This Week",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                completionData.forEachIndexed { index, isCompleted ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Day label
                        Text(
                            text = dayLabels[index],
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Completion indicator
                        Canvas(
                            modifier = Modifier.size(32.dp)
                        ) {
                            val color = if (isCompleted) HabitGreen else Color.Gray.copy(alpha = 0.3f)
                            drawCircle(
                                color = color,
                                radius = size.minDimension / 2,
                                center = Offset(size.width / 2, size.height / 2)
                            )
                            
                            if (isCompleted) {
                                // Draw checkmark
                                val checkSize = size.minDimension * 0.3f
                                val centerX = size.width / 2
                                val centerY = size.height / 2
                                
                                drawLine(
                                    color = Color.White,
                                    start = Offset(centerX - checkSize / 2, centerY),
                                    end = Offset(centerX - checkSize / 4, centerY + checkSize / 2),
                                    strokeWidth = 3.dp.toPx()
                                )
                                drawLine(
                                    color = Color.White,
                                    start = Offset(centerX - checkSize / 4, centerY + checkSize / 2),
                                    end = Offset(centerX + checkSize / 2, centerY - checkSize / 2),
                                    strokeWidth = 3.dp.toPx()
                                )
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Weekly stats
            val completedDays = completionData.count { it }
            val completionRate = (completedDays.toFloat() / 7 * 100).toInt()
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$completedDays/7 days completed",
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
                
                Text(
                    text = "$completionRate%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = HabitGreen
                )
            }
        }
    }
}

