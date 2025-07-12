package com.ashwin.habit_tracker.utils

import com.ashwin.habit_tracker.data.HabitCompletion
import java.util.*

object ProgressAnalytics {
    
    /**
     * Get weekly completion data for the last 7 days
     */
    fun getWeeklyCompletionData(completions: List<HabitCompletion>): List<Boolean> {
        val weeklyData = mutableListOf<Boolean>()
        val calendar = Calendar.getInstance()
        
        // Start from Monday of current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        
        for (i in 0 until 7) {
            val dayStart = DateUtils.getStartOfDay(calendar.time)
            val dayEnd = DateUtils.getEndOfDay(calendar.time)
            
            val hasCompletion = completions.any { completion ->
                completion.completionDate >= dayStart && completion.completionDate <= dayEnd
            }
            
            weeklyData.add(hasCompletion)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        
        return weeklyData
    }
    
    /**
     * Get monthly completion data for the current month
     */
    fun getMonthlyCompletionData(completions: List<HabitCompletion>): List<Boolean> {
        val monthlyData = mutableListOf<Boolean>()
        val calendar = Calendar.getInstance()
        
        // Set to first day of current month
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val currentMonth = calendar.get(Calendar.MONTH)
        
        while (calendar.get(Calendar.MONTH) == currentMonth) {
            val dayStart = DateUtils.getStartOfDay(calendar.time)
            val dayEnd = DateUtils.getEndOfDay(calendar.time)
            
            val hasCompletion = completions.any { completion ->
                completion.completionDate >= dayStart && completion.completionDate <= dayEnd
            }
            
            monthlyData.add(hasCompletion)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        
        return monthlyData
    }
    
    /**
     * Get streak history for the last 30 days
     */
    fun getStreakHistory(completions: List<HabitCompletion>): List<Int> {
        val streakHistory = mutableListOf<Int>()
        val calendar = Calendar.getInstance()
        
        // Start from 30 days ago
        calendar.add(Calendar.DAY_OF_YEAR, -29)
        
        var currentStreak = 0
        
        for (i in 0 until 30) {
            val dayStart = DateUtils.getStartOfDay(calendar.time)
            val dayEnd = DateUtils.getEndOfDay(calendar.time)
            
            val hasCompletion = completions.any { completion ->
                completion.completionDate >= dayStart && completion.completionDate <= dayEnd
            }
            
            if (hasCompletion) {
                currentStreak++
            } else {
                currentStreak = 0
            }
            
            streakHistory.add(currentStreak)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        
        return streakHistory
    }
    
    /**
     * Calculate completion rate for a given period
     */
    fun calculateCompletionRate(completions: List<HabitCompletion>, totalDays: Int): Float {
        return if (totalDays > 0) {
            completions.size.toFloat() / totalDays
        } else {
            0f
        }
    }
    
    /**
     * Get best performing day of the week
     */
    fun getBestDayOfWeek(completions: List<HabitCompletion>): String {
        val dayNames = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val dayCompletions = IntArray(7)
        
        completions.forEach { completion ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = completion.completionDate
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // 0-based index
            dayCompletions[dayOfWeek]++
        }
        
        val bestDayIndex = dayCompletions.indices.maxByOrNull { dayCompletions[it] } ?: 0
        return dayNames[bestDayIndex]
    }
    
    /**
     * Calculate average streak length
     */
    fun calculateAverageStreakLength(completions: List<HabitCompletion>): Float {
        if (completions.isEmpty()) return 0f
        
        val sortedCompletions = completions.sortedBy { it.completionDate }
        val streaks = mutableListOf<Int>()
        var currentStreak = 1
        var previousDate: Date? = null
        
        sortedCompletions.forEach { completion ->
            val completionDate = Date(completion.completionDate)
            
            if (previousDate != null) {
                val daysDifference = ((completionDate.time - previousDate!!.time) / (1000 * 60 * 60 * 24)).toInt()
                
                if (daysDifference == 1) {
                    currentStreak++
                } else {
                    streaks.add(currentStreak)
                    currentStreak = 1
                }
            }
            
            previousDate = completionDate
        }
        
        if (currentStreak > 0) {
            streaks.add(currentStreak)
        }
        
        return if (streaks.isNotEmpty()) {
            streaks.average().toFloat()
        } else {
            0f
        }
    }
}

