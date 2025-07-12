package com.ashwin.habit_tracker.utils

import com.ashwin.habit_tracker.data.HabitCompletion
import java.util.*

object StreakCalculator {
    
    fun calculateCurrentStreak(completions: List<HabitCompletion>): Int {
        if (completions.isEmpty()) return 0
        
        // Sort completions by date (most recent first)
        val sortedCompletions = completions.sortedByDescending { it.completionDate }
        
        var streak = 0
        var currentDate = Date()
        
        // Check if there's a completion for today or yesterday
        val today = DateUtils.getStartOfDay(currentDate)
        val yesterday = DateUtils.getStartOfDay(DateUtils.getPreviousDay(currentDate))
        
        val hasCompletionToday = sortedCompletions.any { 
            DateUtils.isSameDay(it.completionDate, today) 
        }
        val hasCompletionYesterday = sortedCompletions.any { 
            DateUtils.isSameDay(it.completionDate, yesterday) 
        }
        
        // If no completion today or yesterday, streak is 0
        if (!hasCompletionToday && !hasCompletionYesterday) {
            return 0
        }
        
        // Start counting from today if completed, otherwise from yesterday
        if (hasCompletionToday) {
            currentDate = Date()
        } else {
            currentDate = DateUtils.getPreviousDay(Date())
        }
        
        // Count consecutive days
        for (completion in sortedCompletions) {
            val completionDay = DateUtils.getStartOfDay(Date(completion.completionDate))
            val currentDay = DateUtils.getStartOfDay(currentDate)
            
            if (DateUtils.isSameDay(completionDay, currentDay)) {
                streak++
                currentDate = DateUtils.getPreviousDay(currentDate)
            } else if (completionDay < currentDay) {
                // Gap found, break the streak
                break
            }
        }
        
        return streak
    }
    
    fun calculateLongestStreak(completions: List<HabitCompletion>): Int {
        if (completions.isEmpty()) return 0
        
        val sortedCompletions = completions.sortedBy { it.completionDate }
        var longestStreak = 0
        var currentStreak = 0
        var previousDate: Date? = null
        
        for (completion in sortedCompletions) {
            val completionDate = Date(completion.completionDate)
            
            if (previousDate == null) {
                currentStreak = 1
            } else {
                val daysDifference = ((completionDate.time - previousDate.time) / (1000 * 60 * 60 * 24)).toInt()
                
                if (daysDifference == 1) {
                    // Consecutive day
                    currentStreak++
                } else {
                    // Gap found, reset current streak
                    longestStreak = maxOf(longestStreak, currentStreak)
                    currentStreak = 1
                }
            }
            
            previousDate = completionDate
        }
        
        return maxOf(longestStreak, currentStreak)
    }
}

