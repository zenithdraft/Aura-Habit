# Digital Habit Loop Tracker - Project Structure

## 📁 Complete File Structure

```
habit_tracker_app/
├── README.md                           # Main project documentation
├── USER_GUIDE.md                       # User guide and feature overview
├── PROJECT_STRUCTURE.md                # This file - project structure overview
├── build.gradle                        # Project-level build configuration
├── gradle.properties                   # Gradle configuration properties
├── settings.gradle                      # Gradle settings
├── todo.md                             # Development progress tracking
│
└── app/
    ├── build.gradle                     # App-level build configuration
    ├── proguard-rules.pro              # ProGuard rules for release builds
    │
    └── src/main/
        ├── AndroidManifest.xml          # App manifest with permissions
        │
        ├── java/com/ashwin/habit_tracker/
        │   ├── MainActivity.kt           # Main activity entry point
        │   │
        │   ├── data/                    # Database layer
        │   │   ├── Habit.kt             # Habit entity with Room annotations
        │   │   ├── HabitCompletion.kt   # Completion tracking entity
        │   │   ├── HabitDao.kt          # Data access object for habits
        │   │   ├── HabitCompletionDao.kt # DAO for completion tracking
        │   │   └── HabitRoomDatabase.kt # Room database configuration
        │   │
        │   ├── repository/              # Data access layer
        │   │   └── HabitRepository.kt   # Repository pattern implementation
        │   │
        │   ├── viewmodel/               # UI state management
        │   │   └── HabitViewModel.kt    # ViewModel with LiveData/Flow
        │   │
        │   ├── ui/                      # User interface components
        │   │   ├── components/          # Reusable UI components
        │   │   │   ├── HabitCard.kt     # Individual habit display card
        │   │   │   ├── HabitCardUpdated.kt # Enhanced habit card with streaks
        │   │   │   ├── CheckInButton.kt # Check-in interaction component
        │   │   │   ├── WeeklyChart.kt   # Weekly progress visualization
        │   │   │   ├── MonthlyOverview.kt # Monthly calendar view
        │   │   │   ├── StreakVisualization.kt # Streak analytics display
        │   │   │   ├── AnimatedComponents.kt # Smooth animations
        │   │   │   └── AccessibilityComponents.kt # Accessibility support
        │   │   │
        │   │   ├── screens/             # App screens
        │   │   │   ├── HomeScreen.kt    # Main habit list screen
        │   │   │   ├── AddHabitScreen.kt # Habit creation form
        │   │   │   ├── HabitDetailScreen.kt # Individual habit details
        │   │   │   ├── StatisticsScreen.kt # Progress analytics screen
        │   │   │   └── SettingsScreen.kt # App configuration screen
        │   │   │
        │   │   └── theme/               # App theming
        │   │       ├── Color.kt         # Color palette (light/dark)
        │   │       ├── Theme.kt         # Material theme configuration
        │   │       ├── Type.kt          # Typography definitions
        │   │       └── Shape.kt         # Shape definitions
        │   │
        │   ├── navigation/              # App navigation
        │   │   ├── HabitNavigation.kt   # Navigation graph setup
        │   │   └── BottomNavigation.kt  # Bottom navigation bar
        │   │
        │   ├── notifications/           # Notification system
        │   │   └── NotificationHelper.kt # Notification channel setup
        │   │
        │   ├── workers/                 # Background tasks
        │   │   └── HabitReminderWorker.kt # WorkManager reminder worker
        │   │
        │   └── utils/                   # Utility classes
        │       ├── DateUtils.kt         # Date manipulation utilities
        │       ├── StreakCalculator.kt  # Streak calculation logic
        │       ├── ReminderScheduler.kt # Notification scheduling
        │       └── ProgressAnalytics.kt # Progress calculation utilities
        │
        └── res/                         # Android resources
            ├── drawable/                # Vector drawables and icons
            │   └── ic_habit_tracker.xml # App icon vector drawable
            │
            └── values/                  # Resource values
                ├── colors.xml           # Color resources
                ├── strings.xml          # String resources
                ├── themes.xml           # Light theme resources
                └── themes_night.xml     # Dark theme resources
```

## 🏗️ Architecture Overview

### MVVM Architecture Pattern
```
View (Compose UI) ↔ ViewModel ↔ Repository ↔ Database (Room)
                                     ↓
                              Background Workers
```

### Key Components

#### 1. Data Layer (`data/`)
- **Entities**: Room database entities with proper annotations
- **DAOs**: Data Access Objects for database operations
- **Database**: Room database configuration with migrations

#### 2. Repository Layer (`repository/`)
- **HabitRepository**: Abstracts data sources and provides clean API
- **Single Source of Truth**: Centralizes data access logic

#### 3. ViewModel Layer (`viewmodel/`)
- **HabitViewModel**: Manages UI state and business logic
- **Lifecycle Aware**: Survives configuration changes
- **Reactive**: Uses Flow/LiveData for reactive programming

#### 4. UI Layer (`ui/`)
- **Screens**: Full-screen composables for major app sections
- **Components**: Reusable UI components with clear responsibilities
- **Theme**: Consistent design system with light/dark mode support

#### 5. Navigation (`navigation/`)
- **Navigation Graph**: Defines app navigation structure
- **Bottom Navigation**: Tab-based navigation for main sections

#### 6. Background Processing (`workers/`, `notifications/`)
- **WorkManager**: Reliable background task scheduling
- **Notifications**: System notification integration

#### 7. Utilities (`utils/`)
- **Helper Classes**: Common functionality used across the app
- **Analytics**: Progress calculation and data analysis

## 🔧 Technical Implementation Details

### Database Schema
```sql
-- Habits Table
CREATE TABLE habits (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    cue TEXT NOT NULL,
    routine TEXT NOT NULL,
    reward TEXT NOT NULL,
    frequency TEXT NOT NULL,
    reminderTime TEXT,
    createdAt INTEGER NOT NULL
);

-- Habit Completions Table
CREATE TABLE habit_completions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    habitId INTEGER NOT NULL,
    completionDate INTEGER NOT NULL,
    FOREIGN KEY(habitId) REFERENCES habits(id) ON DELETE CASCADE
);
```

### Key Dependencies
```gradle
// Core Android
implementation 'androidx.core:core-ktx:1.8.0'
implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'

// Compose UI
implementation 'androidx.compose.ui:ui:1.2.0'
implementation 'androidx.compose.material:material:1.2.0'
implementation 'androidx.activity:activity-compose:1.5.1'

// Navigation
implementation 'androidx.navigation:navigation-compose:2.5.1'

// ViewModel
implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1'

// Room Database
implementation 'androidx.room:room-runtime:2.4.3'
implementation 'androidx.room:room-ktx:2.4.3'
kapt 'androidx.room:room-compiler:2.4.3'

// WorkManager
implementation 'androidx.work:work-runtime-ktx:2.8.1'

// Charts
implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
```

### Build Configuration
- **Minimum SDK**: 21 (Android 5.0) - 94%+ device coverage
- **Target SDK**: 33 (Android 13) - Latest features and security
- **Compile SDK**: 33 - Latest development tools

### ProGuard Configuration
- Keeps Room database classes
- Preserves WorkManager functionality
- Maintains Compose UI components
- Protects chart library classes

## 🎨 Design System

### Color Palette
- **Primary**: Green (#4CAF50) - Represents growth and success
- **Secondary**: Blue (#2196F3) - Trust and reliability
- **Accent**: Orange (#FF9800) - Energy and motivation
- **Error**: Red (#F44336) - Clear error indication

### Typography
- **Headlines**: Bold, clear hierarchy
- **Body Text**: Readable, appropriate line spacing
- **Captions**: Subtle, informative

### Component Design
- **Cards**: Elevated, rounded corners
- **Buttons**: Clear call-to-action styling
- **Charts**: Clean, informative visualizations

## 🔄 Data Flow

### Habit Creation Flow
1. User fills AddHabitScreen form
2. ViewModel validates and processes data
3. Repository saves to Room database
4. UI updates via reactive Flow

### Check-in Flow
1. User taps check-in button
2. ViewModel toggles completion state
3. Repository updates completion table
4. Streak calculations update automatically
5. UI reflects new state immediately

### Notification Flow
1. User sets reminder time
2. ReminderScheduler creates WorkManager task
3. HabitReminderWorker executes at scheduled time
4. NotificationHelper displays system notification

## 📱 User Experience Features

### Accessibility
- Screen reader support
- Semantic descriptions
- High contrast support
- Touch target sizing

### Performance
- Lazy loading for large lists
- Efficient database queries
- Smooth animations
- Memory-conscious image handling

### Offline Support
- Full offline functionality
- Local data storage
- No network dependencies

## 🚀 Deployment Considerations

### Release Build
- ProGuard enabled for code optimization
- Signed APK for distribution
- Version management
- Crash reporting integration (future)

### Testing Strategy
- Unit tests for business logic
- Integration tests for database
- UI tests for critical flows
- Manual testing on multiple devices

---

This project demonstrates modern Android development best practices with a focus on user experience, maintainable code, and scalable architecture.

