package com.ashwin.habit_tracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddHabitScreen(
    onBackClick: () -> Unit,
    onSaveHabit: (String, String, String, String, String, String?) -> Unit
) {
    var habitName by remember { mutableStateOf("") }
    var cue by remember { mutableStateOf("") }
    var routine by remember { mutableStateOf("") }
    var reward by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("Daily") }
    var reminderTime by remember { mutableStateOf("") }
    
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("Add New Habit") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Habit Name
            OutlinedTextField(
                value = habitName,
                onValueChange = { habitName = it },
                label = { Text("Habit Name") },
                placeholder = { Text("e.g., Drink water after waking up") },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Cue Section
            Text(
                text = "Cue (What triggers this habit?)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                value = cue,
                onValueChange = { cue = it },
                label = { Text("Cue") },
                placeholder = { Text("e.g., After waking up") },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Routine Section
            Text(
                text = "Routine (What is the habit action?)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                value = routine,
                onValueChange = { routine = it },
                label = { Text("Routine") },
                placeholder = { Text("e.g., Drink 1 glass of water") },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Reward Section
            Text(
                text = "Reward (What benefit do you get?)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                value = reward,
                onValueChange = { reward = it },
                label = { Text("Reward") },
                placeholder = { Text("e.g., Feel refreshed and alert") },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Frequency Section
            Text(
                text = "Frequency",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Daily", "Alternate", "Custom").forEach { freq ->
                    FilterChip(
                        onClick = { frequency = freq },
                        selected = frequency == freq,
                        content = { Text(freq) }
                    )
                }
            }
            
            // Reminder Time (Optional)
            OutlinedTextField(
                value = reminderTime,
                onValueChange = { reminderTime = it },
                label = { Text("Reminder Time (Optional)") },
                placeholder = { Text("e.g., 07:30") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Save Button
            Button(
                onClick = {
                    if (habitName.isNotBlank() && cue.isNotBlank() && 
                        routine.isNotBlank() && reward.isNotBlank()) {
                        onSaveHabit(
                            habitName,
                            cue,
                            routine,
                            reward,
                            frequency,
                            if (reminderTime.isNotBlank()) reminderTime else null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = habitName.isNotBlank() && cue.isNotBlank() && 
                         routine.isNotBlank() && reward.isNotBlank()
            ) {
                Text(
                    text = "Save Habit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

