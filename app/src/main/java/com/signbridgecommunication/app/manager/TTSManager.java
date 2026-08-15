package com.signbridgecommunication.app.manager;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TTSManager {
    private static final String TAG = "TTSManager";
    private TextToSpeech tts;
    private boolean isInitialized = false;

    public TTSManager(Context context) {
        tts = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                isInitialized = true;
                tts.setLanguage(Locale.US);
            } else {
                Log.e(TAG, "TTS Initialization failed");
            }
        });
    }

    public void speak(String text, String languageCode, float speed, float pitch) {
        if (!isInitialized) return;

        Locale locale;
        switch (languageCode.toLowerCase()) {
            case "hi":
                locale = new Locale("hi", "IN");
                break;
            case "mr":
                locale = new Locale("mr", "IN");
                break;
            default:
                locale = Locale.US;
                break;
        }

        tts.setLanguage(locale);
        tts.setSpeechRate(speed);
        tts.setPitch(pitch);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void stop() {
        if (tts != null) {
            tts.stop();
        }
    }

    public void shutdown() {
        if (tts != null) {
            tts.shutdown();
        }
    }
}