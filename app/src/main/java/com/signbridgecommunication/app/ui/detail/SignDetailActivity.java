package com.signbridgecommunication.app.ui.detail;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.data.db.SignBridgeDatabase;
import com.signbridgecommunication.app.data.model.Phrase;
import com.signbridgecommunication.app.manager.TTSManager;
import com.signbridgecommunication.app.data.prefs.AppPreferences;

public class SignDetailActivity extends AppCompatActivity {

    private Phrase currentPhrase;
    private TTSManager ttsManager;
    private AppPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail);

        ttsManager = new TTSManager(this);
        prefs = new AppPreferences(this);

        int phraseId = getIntent().getIntExtra("PHRASE_ID", -1);
        if (phraseId != -1) {
            SignBridgeDatabase.databaseWriteExecutor.execute(() -> {
                currentPhrase = SignBridgeDatabase.getDatabase(this).signDao().getPhraseById(phraseId);
                runOnUiThread(this::updateUI);
            });
        }

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_play_audio).setOnClickListener(v -> {
            if (currentPhrase != null) {
                ttsManager.speak(currentPhrase.getEnglishText(), prefs.getLanguage(), prefs.getSpeechSpeed(), prefs.getSpeechPitch());
            }
        });
        
        findViewById(R.id.btn_add_favorite).setOnClickListener(v -> {
            if (currentPhrase != null) {
                currentPhrase.setFavorite(!currentPhrase.isFavorite());
                SignBridgeDatabase.databaseWriteExecutor.execute(() -> {
                    SignBridgeDatabase.getDatabase(this).signDao().updatePhrase(currentPhrase);
                });
                Toast.makeText(this, currentPhrase.isFavorite() ? "Added to Favorites" : "Removed from Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        if (currentPhrase != null) {
            TextView tvTitle = findViewById(R.id.tv_sign_title);
            TextView tvDesc = findViewById(R.id.tv_sign_desc);
            tvTitle.setText(currentPhrase.getEnglishText());
            tvDesc.setText(currentPhrase.getDescription());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutdown();
    }
}