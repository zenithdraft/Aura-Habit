# Digital Habit Loop Tracker

A comprehensive Android app designed to help users build better habits using the proven "Habit Loop" model by Charles Duhigg. The app focuses on the psychology behind habit formation: **Cue → Routine → Reward**.

## 🎯 Key Features

### Core Functionality
- **Habit Creation**: Define habits with clear Cue, Routine, and Reward components
- **Daily Check-ins**: Simple tap-to-complete habit tracking
- **Streak Tracking**: Visual streak counters to maintain motivation
- **Smart Reminders**: Customizable notifications at specified times

### Progress Visualization
- **Weekly Charts**: Visual completion tracking for the current week
- **Monthly Calendar**: Month-view completion patterns
- **Streak Analytics**: Current vs. longest streak comparisons
- **Progress Statistics**: Comprehensive analytics and insights

### User Experience
- **Dark Mode Support**: Automatic system theme detection
- **Smooth Animations**: Polished interactions and transitions
- **Accessibility**: Full screen reader and accessibility support
- **Bottom Navigation**: Intuitive app navigation

## 🏗️ Technical Architecture

### Technology Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Database**: Room (SQLite)
- **Architecture**: MVVM with Repository pattern
- **Notifications**: WorkManager
- **Charts**: MPAndroidChart
- **Navigation**: Navigation Compose

### Project Structure
```
app/src/main/java/com/ashwin/habit_tracker/
├── data/                    # Database entities and DAOs
│   ├── Habit.kt
│   ├── HabitCompletion.kt
│   ├── HabitDao.kt
│   ├── HabitCompletionDao.kt
│   └── HabitRoomDatabase.kt
├── repository/              # Data access layer
│   └── HabitRepository.kt
├── viewmodel/              # UI state management
│   └── HabitViewModel.kt
├── ui/
│   ├── components/         # Reusable UI components
│   │   ├── HabitCard.kt
│   │   ├── CheckInButton.kt
│   │   ├── WeeklyChart.kt
│   │   ├── MonthlyOverview.kt
│   │   ├── StreakVisualization.kt
│   │   ├── AnimatedComponents.kt
│   │   └── AccessibilityComponents.kt
│   ├── screens/           # App screens
│   │   ├── HomeScreen.kt
│   │   ├── AddHabitScreen.kt
│   │   ├── HabitDetailScreen.kt
│   │   ├── StatisticsScreen.kt
│   │   └── SettingsScreen.kt
│   └── theme/             # App theming
│       ├── Color.kt
│       ├── Theme.kt
│       ├── Type.kt
│       └── Shape.kt
├── navigation/            # App navigation
│   ├── HabitNavigation.kt
│   └── BottomNavigation.kt
├── notifications/         # Notification system
│   └── NotificationHelper.kt
├── workers/              # Background tasks
│   └── HabitReminderWorker.kt
└── utils/                # Utility classes
    ├── DateUtils.kt
    ├── StreakCalculator.kt
    ├── ReminderScheduler.kt
    └── ProgressAnalytics.kt
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 21+ (Android 5.0+)
- Kotlin 1.7.0+

### Installation
1. Clone or download the project
2. Open in Android Studio
3. Sync Gradle files
4. Run on device or emulator

### Build Configuration
The app is configured with:
- **Minimum SDK**: 21 (Android 5.0)
- **Target SDK**: 33 (Android 13)
- **Compile SDK**: 33

## 📱 User Guide

### Creating Your First Habit
1. Tap the **"+"** button on the home screen
2. Fill in the habit details:
   - **Habit Name**: What you want to do
   - **Cue**: What triggers this habit
   - **Routine**: The actual habit action
   - **Reward**: What benefit you get
   - **Frequency**: How often (Daily, Alternate, Custom)
   - **Reminder Time**: Optional notification time

### Daily Check-ins
- Tap the circular button next to each habit to mark it complete
- Completed habits show a green checkmark
- Your streak counter updates automatically

### Viewing Progress
- Navigate to the **Statistics** tab
- View weekly completion charts
- See monthly calendar patterns
- Track current and longest streaks

### Settings & Customization
- Access settings via the bottom navigation
- Toggle notifications on/off
- Customize app theme
- Export/import data

## 🧠 The Science Behind the App

### Habit Loop Model
Based on Charles Duhigg's research in "The Power of Habit":

1. **Cue**: Environmental trigger that initiates the habit
2. **Routine**: The behavior or action itself
3. **Reward**: The benefit that reinforces the habit

### Psychology Features
- **Streak Tracking**: Leverages loss aversion psychology
- **Visual Progress**: Provides immediate feedback
- **Cue Awareness**: Helps identify habit triggers
- **Reward Recognition**: Reinforces positive outcomes

## 🔧 Development Notes

### Database Schema
- **Habits Table**: Stores habit definitions
- **Habit Completions Table**: Tracks daily completions
- **Foreign Key Relationships**: Maintains data integrity

### Notification System
- Uses WorkManager for reliable scheduling
- Supports daily recurring reminders
- Handles app updates and device reboots

### Performance Considerations
- Room database for efficient local storage
- Coroutines for non-blocking operations
- Compose for modern, efficient UI rendering

## 🎨 Design Philosophy

### User-Centered Design
- Minimal cognitive load
- Clear visual hierarchy
- Consistent interaction patterns
- Accessibility-first approach

### Visual Design
- Material Design 3 principles
- Consistent color palette
- Meaningful animations
- Dark mode support

## 📈 Future Enhancements

### Potential Features
- Habit categories and tags
- Social sharing and challenges
- Advanced analytics and insights
- Habit templates and suggestions
- Cloud sync and backup
- Widget support

### Technical Improvements
- Unit and integration tests
- Performance optimizations
- Offline-first architecture
- Advanced notification scheduling

## 🤝 Contributing

This app was built as a complete demonstration of modern Android development practices. The codebase follows:
- Clean Architecture principles
- SOLID design patterns
- Material Design guidelines
- Accessibility standards

## 📄 License

This project is created for educational and demonstration purposes.

## 👨‍💻 Developer

Built by Manus AI for Ashwin, demonstrating a complete Android app development workflow from concept to delivery.

---

**Happy Habit Building! 🌟**

