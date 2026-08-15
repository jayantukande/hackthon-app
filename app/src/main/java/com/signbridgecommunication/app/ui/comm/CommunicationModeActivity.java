package com.signbridgecommunication.app.ui.comm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.manager.TTSManager;
import com.signbridgecommunication.app.data.prefs.AppPreferences;

public class CommunicationModeActivity extends AppCompatActivity {

    private TTSManager ttsManager;
    private AppPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_mode);

        ttsManager = new TTSManager(this);
        prefs = new AppPreferences(this);

        String phrase = getIntent().getStringExtra("PHRASE_TEXT");
        if (phrase == null) phrase = "HELLO";

        TextView tvPhrase = findViewById(R.id.tv_large_phrase);
        tvPhrase.setText(phrase);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        
        String finalPhrase = phrase;
        findViewById(R.id.btn_speak).setOnClickListener(v -> {
            ttsManager.speak(finalPhrase, prefs.getLanguage(), prefs.getSpeechSpeed(), prefs.getSpeechPitch());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutdown();
    }
}