package com.ashwin.habit_tracker.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ashwin.habit_tracker.MainActivity
import com.ashwin.habit_tracker.R

object NotificationHelper {
    
    const val HABIT_REMINDER_CHANNEL_ID = "habit_reminder_channel"
    const val HABIT_REMINDER_CHANNEL_NAME = "Habit Reminders"
    const val HABIT_REMINDER_NOTIFICATION_ID = 1001
    
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                HABIT_REMINDER_CHANNEL_ID,
                HABIT_REMINDER_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for habit reminders"
                enableVibration(true)
                setShowBadge(true)
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    fun createHabitReminderNotification(
        context: Context,
        habitName: String,
        habitCue: String
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, HABIT_REMINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Time for your habit!")
            .setContentText("$habitCue - $habitName")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("$habitCue\n\nIt's time to: $habitName")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(HABIT_REMINDER_NOTIFICATION_ID, notification)
    }
}

