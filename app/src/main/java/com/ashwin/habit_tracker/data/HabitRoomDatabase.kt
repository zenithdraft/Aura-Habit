package com.ashwin.habit_tracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habit::class, HabitCompletion::class], version = 2, exportSchema = false)
abstract class HabitRoomDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    abstract fun habitCompletionDao(): HabitCompletionDao

    companion object {
        @Volatile
        private var INSTANCE: HabitRoomDatabase? = null

        fun getDatabase(context: Context): HabitRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitRoomDatabase::class.java,
                    "habit_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


