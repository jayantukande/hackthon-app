package com.signbridgecommunication.app.ui.editprofile;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.data.prefs.AppPreferences;

public class EditProfileActivity extends AppCompatActivity {

    private AppPreferences prefs;
    private TextInputEditText etName, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        prefs = new AppPreferences(this);
        etName = findViewById(R.id.et_edit_name);
        etEmail = findViewById(R.id.et_edit_email);

        etName.setText(prefs.getUserName());
        etEmail.setText(prefs.getUserEmail());

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        findViewById(R.id.btn_save_profile).setOnClickListener(v -> {
            String newName = etName.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();

            if (!newName.isEmpty() && !newEmail.isEmpty()) {
                prefs.setUserName(newName);
                prefs.setUserEmail(newEmail);
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}