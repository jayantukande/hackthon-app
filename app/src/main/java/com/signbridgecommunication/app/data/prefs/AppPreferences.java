package com.signbridgecommunication.app.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String PREF_NAME = "signbridge_prefs";
    private static final String KEY_THEME = "theme";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_TEXT_SIZE = "text_size";
    private static final String KEY_SPEECH_SPEED = "speech_speed";
    private static final String KEY_SPEECH_PITCH = "speech_pitch";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";

    private SharedPreferences prefs;

    public AppPreferences(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setTheme(int theme) { prefs.edit().putInt(KEY_THEME, theme).apply(); }
    public int getTheme() { return prefs.getInt(KEY_THEME, 0); } // 0: System, 1: Light, 2: Dark

    public void setLanguage(String lang) { prefs.edit().putString(KEY_LANGUAGE, lang).apply(); }
    public String getLanguage() { return prefs.getString(KEY_LANGUAGE, "en"); }

    public void setTextSize(String size) { prefs.edit().putString(KEY_TEXT_SIZE, size).apply(); }
    public String getTextSize() { return prefs.getString(KEY_TEXT_SIZE, "Medium"); }

    public void setSpeechSpeed(float speed) { prefs.edit().putFloat(KEY_SPEECH_SPEED, speed).apply(); }
    public float getSpeechSpeed() { return prefs.getFloat(KEY_SPEECH_SPEED, 1.0f); }

    public void setSpeechPitch(float pitch) { prefs.edit().putFloat(KEY_SPEECH_PITCH, pitch).apply(); }
    public float getSpeechPitch() { return prefs.getFloat(KEY_SPEECH_PITCH, 1.0f); }

    public void setUserName(String name) { prefs.edit().putString(KEY_USER_NAME, name).apply(); }
    public String getUserName() { return prefs.getString(KEY_USER_NAME, "User"); }

    public void setUserEmail(String email) { prefs.edit().putString(KEY_USER_EMAIL, email).apply(); }
    public String getUserEmail() { return prefs.getString(KEY_USER_EMAIL, "user@example.com"); }
}