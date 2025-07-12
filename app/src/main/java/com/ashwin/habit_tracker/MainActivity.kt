package com.ashwin.habit_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ashwin.habit_tracker.navigation.HabitNavigation
import com.ashwin.habit_tracker.ui.theme.HabitTrackerTheme
import com.ashwin.habit_tracker.viewmodel.HabitViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val habitViewModel: HabitViewModel = viewModel()
                    HabitNavigation(habitViewModel = habitViewModel)
                }
            }
        }
    }
}

