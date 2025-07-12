package com.ashwin.habit_tracker.repository

import androidx.annotation.WorkerThread
import com.ashwin.habit_tracker.data.Habit
import com.ashwin.habit_tracker.data.HabitDao
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {

    // Observed Flow will notify the observer when the data has changed.
    val allHabits: Flow<List<Habit>> = habitDao.getHabits()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(habit: Habit) {
        habitDao.insert(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(habit: Habit) {
        habitDao.update(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(habit: Habit) {
        habitDao.delete(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getHabitById(id: Int): Habit? {
        return habitDao.getHabitById(id)
    }
}

