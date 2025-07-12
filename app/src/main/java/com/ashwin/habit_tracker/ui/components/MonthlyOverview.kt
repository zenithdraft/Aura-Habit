package com.ashwin.habit_tracker.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.ui.theme.HabitGreen
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil

@Composable
fun MonthlyOverview(
    monthlyData: List<Boolean>, // Up to 31 days of completion data
    month: String = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date()),
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
                text = month,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Calendar grid
            val rows = ceil(monthlyData.size / 7.0).toInt()
            
            Column {
                // Week day headers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                        Box(
                            modifier = Modifier.size(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day,
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Calendar days
                for (week in 0 until rows) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (day in 0 until 7) {
                            val dayIndex = week * 7 + day
                            
                            Box(
                                modifier = Modifier.size(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if (dayIndex < monthlyData.size) {
                                    val isCompleted = monthlyData[dayIndex]
                                    
                                    Canvas(
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        val color = when {
                                            isCompleted -> HabitGreen
                                            else -> Color.Gray.copy(alpha = 0.2f)
                                        }
                                        
                                        drawCircle(
                                            color = color,
                                            radius = size.minDimension / 2,
                                            center = Offset(size.width / 2, size.height / 2)
                                        )
                                    }
                                    
                                    Text(
                                        text = (dayIndex + 1).toString(),
                                        fontSize = 10.sp,
                                        color = if (isCompleted) Color.White else MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                                    )
                                }
                            }
                        }
                    }
                    
                    if (week < rows - 1) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Monthly stats
            val completedDays = monthlyData.count { it }
            val totalDays = monthlyData.size
            val completionRate = if (totalDays > 0) (completedDays.toFloat() / totalDays * 100).toInt() else 0
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Completed Days",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "$completedDays/$totalDays",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Success Rate",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "$completionRate%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = HabitGreen
                    )
                }
            }
        }
    }
}

