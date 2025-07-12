package com.ashwin.habit_tracker.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashwin.habit_tracker.ui.screens.AddHabitScreen
import com.ashwin.habit_tracker.ui.screens.HomeScreen
import com.ashwin.habit_tracker.ui.screens.StatisticsScreen
import com.ashwin.habit_tracker.ui.screens.SettingsScreen
import com.ashwin.habit_tracker.viewmodel.HabitViewModel

@Composable
fun HabitNavigation(
    navController: NavHostController = rememberNavController(),
    habitViewModel: HabitViewModel = viewModel()
) {
    val habits by habitViewModel.allHabits.collectAsState()
    
    Scaffold(
        bottomBar = {
            // Only show bottom navigation on main screens
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute in listOf("home", "statistics", "settings")) {
                HabitBottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen(
                    habits = habits,
                    onAddHabitClick = {
                        navController.navigate("add_habit")
                    },
                    onHabitClick = { habit ->
                        navController.navigate("habit_detail/${habit.id}")
                    }
                )
            }
            
            composable("statistics") {
                StatisticsScreen(
                    habits = habits,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    getWeeklyData = { habitId ->
                        // Placeholder - would get real data from ViewModel
                        listOf(true, false, true, true, false, true, true)
                    },
                    getMonthlyData = { habitId ->
                        // Placeholder - would get real data from ViewModel
                        (1..30).map { it % 3 == 0 }
                    },
                    getCurrentStreak = { habitId ->
                        // Placeholder - would get real data from ViewModel
                        5
                    },
                    getLongestStreak = { habitId ->
                        // Placeholder - would get real data from ViewModel
                        12
                    },
                    getStreakHistory = { habitId ->
                        // Placeholder - would get real data from ViewModel
                        (1..30).map { (it % 7) }
                    }
                )
            }
            
            composable("settings") {
                SettingsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
            
            composable("add_habit") {
                AddHabitScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onSaveHabit = { name, cue, routine, reward, frequency, reminderTime ->
                        habitViewModel.addHabit(name, cue, routine, reward, frequency, reminderTime)
                        navController.popBackStack()
                    }
                )
            }
            
            composable("habit_detail/{habitId}") { backStackEntry ->
                val habitId = backStackEntry.arguments?.getString("habitId")?.toIntOrNull() ?: 0
                // Placeholder for habit detail screen
                // Would implement HabitDetailScreen here
            }
        }
    }
}

