package com.ashwin.habit_tracker.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ashwin.habit_tracker.data.HabitRoomDatabase
import com.ashwin.habit_tracker.notifications.NotificationHelper

class HabitReminderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        return try {
            val habitId = inputData.getInt("habit_id", -1)
            if (habitId == -1) {
                return Result.failure()
            }
            
            val database = HabitRoomDatabase.getDatabase(applicationContext)
            val habit = database.habitDao().getHabitById(habitId)
            
            if (habit != null) {
                NotificationHelper.createHabitReminderNotification(
                    context = applicationContext,
                    habitName = habit.name,
                    habitCue = habit.cue
                )
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}

