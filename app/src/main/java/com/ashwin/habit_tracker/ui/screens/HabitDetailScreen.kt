package com.ashwin.habit_tracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.data.Habit
import com.ashwin.habit_tracker.data.HabitCompletion
import com.ashwin.habit_tracker.ui.components.CheckInButton
import com.ashwin.habit_tracker.ui.theme.HabitGreen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HabitDetailScreen(
    habit: Habit,
    completions: List<HabitCompletion>,
    isCompletedToday: Boolean,
    currentStreak: Int,
    longestStreak: Int,
    onBackClick: () -> Unit,
    onCheckIn: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text(habit.name) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Habit Loop Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Habit Loop",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    // Cue
                    Row {
                        Text(
                            text = "🔔 Cue: ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = habit.cue,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Routine
                    Row {
                        Text(
                            text = "⚡ Routine: ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = habit.routine,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Reward
                    Row {
                        Text(
                            text = "🎁 Reward: ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = habit.reward,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Stats and Check-in Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Stats
                Column {
                    Text(
                        text = "Current Streak",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "$currentStreak days",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = HabitGreen
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Longest Streak",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "$longestStreak days",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                // Check-in Button
                CheckInButton(
                    isCompleted = isCompletedToday,
                    currentStreak = currentStreak,
                    onCheckIn = onCheckIn,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Completion History
            Text(
                text = "Recent Completions",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            if (completions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No completions yet. Start your streak today!",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(completions.take(10)) { completion ->
                        CompletionItem(completion = completion)
                    }
                }
            }
        }
    }
}

@Composable
fun CompletionItem(completion: HabitCompletion) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val date = Date(completion.completionDate)
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "✅",
                fontSize = 16.sp
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column {
                Text(
                    text = dateFormat.format(date),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                
                if (completion.notes != null) {
                    Text(
                        text = completion.notes,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

