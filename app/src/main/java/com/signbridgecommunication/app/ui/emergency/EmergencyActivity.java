package com.signbridgecommunication.app.ui.emergency;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.ui.comm.CommunicationModeActivity;
import android.content.Intent;

public class EmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        setupEmergencyButton(R.id.btn_help, "HELP");
        setupEmergencyButton(R.id.btn_doctor, "I NEED A DOCTOR");
        setupEmergencyButton(R.id.btn_ambulance, "CALL AN AMBULANCE");
        setupEmergencyButton(R.id.btn_police, "CALL THE POLICE");
        setupEmergencyButton(R.id.btn_injured, "I AM INJURED");
        setupEmergencyButton(R.id.btn_water, "I NEED WATER");
    }

    private void setupEmergencyButton(int id, String phrase) {
        findViewById(id).setOnClickListener(v -> {
            Intent intent = new Intent(this, CommunicationModeActivity.class);
            intent.putExtra("PHRASE_TEXT", phrase);
            startActivity(intent);
        });
    }
}