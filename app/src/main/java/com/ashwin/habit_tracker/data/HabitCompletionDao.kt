package com.ashwin.habit_tracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitCompletionDao {
    @Query("SELECT * FROM habit_completions WHERE habitId = :habitId ORDER BY completionDate DESC")
    fun getCompletionsForHabit(habitId: Int): Flow<List<HabitCompletion>>

    @Query("SELECT * FROM habit_completions WHERE habitId = :habitId AND completionDate >= :startDate AND completionDate <= :endDate")
    suspend fun getCompletionsForHabitInDateRange(habitId: Int, startDate: Long, endDate: Long): List<HabitCompletion>

    @Query("SELECT * FROM habit_completions WHERE habitId = :habitId AND completionDate >= :startOfDay AND completionDate <= :endOfDay LIMIT 1")
    suspend fun getCompletionForToday(habitId: Int, startOfDay: Long, endOfDay: Long): HabitCompletion?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(completion: HabitCompletion)

    @Query("DELETE FROM habit_completions WHERE habitId = :habitId AND completionDate >= :startOfDay AND completionDate <= :endOfDay")
    suspend fun deleteCompletionForToday(habitId: Int, startOfDay: Long, endOfDay: Long)
}

