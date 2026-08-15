# Implementation Plan - SignBridge Final Integration

As Team Member 4, I will complete the SignBridge project, ensuring all hackathon requirements are met and the UI matches the provided references.

## User Review Required

> [!IMPORTANT]
> The project appears to be in a very early state despite the "existing project" description. I will be creating the core architecture (Room, DataStore, Navigation) to support the requested features, assuming these are the "missing" parts I am tasked to "complete".

## Proposed Changes

### 1. Data & Persistence Layer
- **[NEW] [SignBridgeDatabase.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/data/SignBridgeDatabase.java)**: Room database for Phrases, Categories, and Favorites.
- **[NEW] [Phrase.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/data/model/Phrase.java)**: Entity for signs/phrases.
- **[NEW] [Category.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/data/model/Category.java)**: Entity for phrase categories.
- **[NEW] [UserPreferences.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/data/prefs/UserPreferences.java)**: DataStore implementation for Theme, Language, Text Size, and TTS settings.

### 2. Core Managers
- **[NEW] [TTSManager.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/manager/TTSManager.java)**: "Existing" manager for speech synthesis.
- **[NEW] [LanguageManager.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/manager/LanguageManager.java)**: Handles English, Hindi, and Marathi translations.

### 3. UI Implementation (Fragments)
- **[NEW] [HomeFragment.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/home/HomeFragment.java)**: Main dashboard.
- **[NEW] [ExploreFragment.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/explore/ExploreFragment.java)**: Category-based sign list.
- **[NEW] [FavoritesFragment.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/favorites/FavoritesFragment.java)**: Saved signs.
- **[NEW] [ProfileFragment.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/profile/ProfileFragment.java)**: Profile and Settings.

### 4. Specialized Activities
- **[NEW] [CommunicationModeActivity.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/comm/CommunicationModeActivity.java)**: Large-format display for quick communication.
- **[NEW] [EmergencyActivity.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/emergency/EmergencyActivity.java)**: Quick-access emergency phrases.
- **[NEW] [SignDetailActivity.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/ui/detail/SignDetailActivity.java)**: Detailed view of a sign with animation/audio.

### 5. Integration
- **[MODIFY] [MainActivity.java](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/java/com/signbridgecommunication/app/MainActivity.java)**: Add `BottomNavigationView` and Fragment hosting.
- **[MODIFY] [AndroidManifest.xml](file:///C:/Users/LOQ/OneDrive/Desktop/android/app/src/main/AndroidManifest.xml)**: Register new Activities and handle configuration changes (Language/Theme).

## Verification Plan

### Automated Tests
- Build APK using `gradlew assembleDebug`.
- Verify database migrations and preference persistence.

### Manual Verification
- **Navigation**: Verify Bottom Nav switches between Home, Explore, Favorites, Profile.
- **Settings**: Verify Theme, Language, and Text Size changes apply globally and persist.
- **TTS**: Test speech output with varying speed/pitch.
- **Communication Mode**: Verify large text and sign display.
- **Emergency**: Verify one-tap access to critical phrases.
- **Offline**: Disable internet and verify core features (Search, Favorites, TTS) still work.
