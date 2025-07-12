package com.ashwin.habit_tracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val cue: String,
    val routine: String,
    val reward: String,
    val frequency: String, // e.g., "Daily", "Alternate", "Custom"
    val reminderTime: String? // e.g., "07:30"
)


