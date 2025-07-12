package com.ashwin.habit_tracker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "habit_completions",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HabitCompletion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val habitId: Int,
    val completionDate: Long, // Store as timestamp
    val notes: String? = null // Optional notes for the completion
)

