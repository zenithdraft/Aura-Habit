package com.ashwin.habit_tracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashwin.habit_tracker.data.Habit
import com.ashwin.habit_tracker.ui.components.MonthlyOverview
import com.ashwin.habit_tracker.ui.components.StreakVisualization
import com.ashwin.habit_tracker.ui.components.WeeklyChart

@Composable
fun StatisticsScreen(
    habits: List<Habit>,
    onBackClick: () -> Unit,
    getWeeklyData: (Int) -> List<Boolean>,
    getMonthlyData: (Int) -> List<Boolean>,
    getCurrentStreak: (Int) -> Int,
    getLongestStreak: (Int) -> Int,
    getStreakHistory: (Int) -> List<Int>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("Statistics") },
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
            if (habits.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = "No habits to analyze yet.\nCreate your first habit to see statistics!",
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            } else {
                items(habits) { habit ->
                    HabitStatisticsCard(
                        habit = habit,
                        weeklyData = getWeeklyData(habit.id),
                        monthlyData = getMonthlyData(habit.id),
                        currentStreak = getCurrentStreak(habit.id),
                        longestStreak = getLongestStreak(habit.id),
                        streakHistory = getStreakHistory(habit.id)
                    )
                }
            }
        }
    }
}

@Composable
fun HabitStatisticsCard(
    habit: Habit,
    weeklyData: List<Boolean>,
    monthlyData: List<Boolean>,
    currentStreak: Int,
    longestStreak: Int,
    streakHistory: List<Int>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 6.dp,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Habit Name
            Text(
                text = habit.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Weekly Chart
            WeeklyChart(
                completionData = weeklyData,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Streak Visualization
            StreakVisualization(
                currentStreak = currentStreak,
                longestStreak = longestStreak,
                streakHistory = streakHistory,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Monthly Overview
            MonthlyOverview(
                monthlyData = monthlyData,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

