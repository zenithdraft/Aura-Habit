package com.ashwin.habit_tracker.utils

import android.content.Context
import androidx.work.*
import com.ashwin.habit_tracker.workers.HabitReminderWorker
import java.util.*
import java.util.concurrent.TimeUnit

object ReminderScheduler {
    
    fun scheduleHabitReminder(
        context: Context,
        habitId: Int,
        reminderTime: String // Format: "HH:mm"
    ) {
        try {
            val timeParts = reminderTime.split(":")
            val hour = timeParts[0].toInt()
            val minute = timeParts[1].toInt()
            
            val currentTime = Calendar.getInstance()
            val reminderCalendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            
            // If the reminder time has passed today, schedule for tomorrow
            if (reminderCalendar.timeInMillis <= currentTime.timeInMillis) {
                reminderCalendar.add(Calendar.DAY_OF_YEAR, 1)
            }
            
            val delay = reminderCalendar.timeInMillis - currentTime.timeInMillis
            
            val inputData = Data.Builder()
                .putInt("habit_id", habitId)
                .build()
            
            val workRequest = PeriodicWorkRequestBuilder<HabitReminderWorker>(
                1, TimeUnit.DAYS
            )
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(inputData)
                .addTag("habit_reminder_$habitId")
                .build()
            
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    "habit_reminder_$habitId",
                    ExistingPeriodicWorkPolicy.REPLACE,
                    workRequest
                )
        } catch (e: Exception) {
            // Handle invalid time format
            e.printStackTrace()
        }
    }
    
    fun cancelHabitReminder(context: Context, habitId: Int) {
        WorkManager.getInstance(context)
            .cancelUniqueWork("habit_reminder_$habitId")
    }
    
    fun cancelAllHabitReminders(context: Context) {
        WorkManager.getInstance(context)
            .cancelAllWorkByTag("habit_reminder")
    }
}

