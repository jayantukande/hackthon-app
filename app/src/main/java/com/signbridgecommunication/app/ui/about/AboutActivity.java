package com.signbridgecommunication.app.ui.about;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.signbridgecommunication.app.R;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}