# SignBridge Hackathon Project - Final Handoff

## Overview
SignBridge is an accessibility platform designed to bridge communication gaps using sign language resources and Text-to-Speech (TTS).

## Features
- **Login/Register**: Initial authentication flow.
- **Home Dashboard**: Quick access to learn signs, TTS, and emergency mode.
- **Emergency Mode**: One-tap access to critical phrases (Help, Doctor, Police, etc.).
- **Communication Mode**: Large-format text display for quick interaction with others.
- **Search & Explore**: Categorized sign library with search functionality.
- **Favorites**: Save frequently used phrases for offline access.
- **Profile & Settings**: 
    - Multi-language support (English, Hindi, Marathi).
    - Customizable TTS (Speed & Pitch).
    - Theme switching (Light, Dark, System).
    - Local profile editing.
- **Offline Support**: Core database and TTS functionality fully functional without internet.

## Architecture
- **Language**: Java
- **Database**: Room (Categories and Phrases)
- **Persistence**: SharedPreferences (AppPreferences)
- **UI Toolkit**: Material Design 3
- **Navigation**: BottomNavigationView + Fragment Manager

## Final Build Instructions
1. Open the project in Android Studio.
2. Perform a **Gradle Sync**.
3. Run `gradlew assembleDebug` to generate the APK.
4. Deploy to an Android emulator or device (Min SDK 24).

## Known Limitations
- Media assets (sign videos/images) are currently mocked using local resources.
- TTS requires language data to be installed on the device for Hindi/Marathi support.
