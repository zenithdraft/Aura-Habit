package com.ashwin.habit_tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ashwin.habit_tracker.data.Habit
import com.ashwin.habit_tracker.data.HabitCompletion
import com.ashwin.habit_tracker.data.HabitRoomDatabase
import com.ashwin.habit_tracker.notifications.NotificationHelper
import com.ashwin.habit_tracker.repository.HabitRepository
import com.ashwin.habit_tracker.utils.DateUtils
import com.ashwin.habit_tracker.utils.ReminderScheduler
import com.ashwin.habit_tracker.utils.StreakCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HabitRepository
    private val completionDao = HabitRoomDatabase.getDatabase(application).habitCompletionDao()

    val allHabits: Flow<List<Habit>>

    init {
        val habitDao = HabitRoomDatabase.getDatabase(application).habitDao()
        repository = HabitRepository(habitDao)
        allHabits = repository.allHabits
        
        // Create notification channel
        NotificationHelper.createNotificationChannel(application)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addHabit(name: String, cue: String, routine: String, reward: String, frequency: String, reminderTime: String?) = viewModelScope.launch(Dispatchers.IO) {
        val habit = Habit(
            name = name,
            cue = cue,
            routine = routine,
            reward = reward,
            frequency = frequency,
            reminderTime = reminderTime
        )
        repository.insert(habit)
        
        // Schedule reminder if time is provided
        if (reminderTime != null && reminderTime.isNotBlank()) {
            // Get the inserted habit ID (this is a simplified approach)
            // In a real app, you might want to return the ID from the insert operation
            val insertedHabit = repository.allHabits
            // Schedule reminder with a generated ID (for demo purposes)
            ReminderScheduler.scheduleHabitReminder(
                getApplication(),
                habit.hashCode(), // Using hashCode as a simple ID
                reminderTime
            )
        }
    }

    /**
     * Update an existing habit
     */
    fun updateHabit(habit: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(habit)
        
        // Update reminder
        if (habit.reminderTime != null && habit.reminderTime.isNotBlank()) {
            ReminderScheduler.scheduleHabitReminder(
                getApplication(),
                habit.id,
                habit.reminderTime
            )
        } else {
            ReminderScheduler.cancelHabitReminder(getApplication(), habit.id)
        }
    }

    /**
     * Delete a habit
     */
    fun deleteHabit(habit: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(habit)
        ReminderScheduler.cancelHabitReminder(getApplication(), habit.id)
    }

    /**
     * Get a habit by ID
     */
    suspend fun getHabitById(id: Int): Habit? {
        return repository.getHabitById(id)
    }
    
    /**
     * Check in for a habit today
     */
    fun checkInHabit(habitId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val today = Date()
        val startOfDay = DateUtils.getStartOfDay(today)
        val endOfDay = DateUtils.getEndOfDay(today)
        
        // Check if already completed today
        val existingCompletion = completionDao.getCompletionForToday(habitId, startOfDay, endOfDay)
        
        if (existingCompletion == null) {
            // Add completion
            val completion = HabitCompletion(
                habitId = habitId,
                completionDate = System.currentTimeMillis()
            )
            completionDao.insert(completion)
        } else {
            // Remove completion (toggle)
            completionDao.deleteCompletionForToday(habitId, startOfDay, endOfDay)
        }
    }
    
    /**
     * Get completions for a habit
     */
    fun getCompletionsForHabit(habitId: Int): Flow<List<HabitCompletion>> {
        return completionDao.getCompletionsForHabit(habitId)
    }
    
    /**
     * Check if habit is completed today
     */
    suspend fun isHabitCompletedToday(habitId: Int): Boolean {
        val today = Date()
        val startOfDay = DateUtils.getStartOfDay(today)
        val endOfDay = DateUtils.getEndOfDay(today)
        
        return completionDao.getCompletionForToday(habitId, startOfDay, endOfDay) != null
    }
    
    /**
     * Calculate current streak for a habit
     */
    suspend fun getCurrentStreak(habitId: Int): Int {
        val completions = completionDao.getCompletionsForHabit(habitId)
        // This would need to be converted to a suspend function or handled differently
        // For now, returning 0 as placeholder
        return 0
    }
}

