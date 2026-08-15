package com.signbridgecommunication.app.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.data.prefs.AppPreferences;
import com.signbridgecommunication.app.ui.editprofile.EditProfileActivity;
import com.signbridgecommunication.app.ui.about.AboutActivity;

import androidx.appcompat.app.AlertDialog;
import com.signbridgecommunication.app.manager.LanguageManager;

public class ProfileFragment extends Fragment {

    private AppPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        prefs = new AppPreferences(requireContext());

        TextView tvName = view.findViewById(R.id.tv_profile_name);
        TextView tvEmail = view.findViewById(R.id.tv_profile_email);
        TextView tvLang = view.findViewById(R.id.tv_current_language);
        TextView tvTheme = view.findViewById(R.id.tv_current_theme);

        tvName.setText(prefs.getUserName());
        tvEmail.setText(prefs.getUserEmail());
        
        String langCode = prefs.getLanguage();
        tvLang.setText(langCode.equals("hi") ? "Hindi" : (langCode.equals("mr") ? "Marathi" : "English"));
        tvTheme.setText(prefs.getTheme() == 0 ? "System" : (prefs.getTheme() == 1 ? "Light" : "Dark"));

        view.findViewById(R.id.setting_language).setOnClickListener(v -> {
            String[] languages = {"English", "Hindi", "Marathi"};
            new AlertDialog.Builder(requireContext())
                    .setTitle("Select Language")
                    .setItems(languages, (dialog, which) -> {
                        String code = which == 0 ? "en" : (which == 1 ? "hi" : "mr");
                        prefs.setLanguage(code);
                        tvLang.setText(languages[which]);
                        Toast.makeText(getContext(), "Language set to " + languages[which], Toast.LENGTH_SHORT).show();
                    }).show();
        });

        view.findViewById(R.id.setting_theme).setOnClickListener(v -> {
            String[] themes = {"System Default", "Light", "Dark"};
            new AlertDialog.Builder(requireContext())
                    .setTitle("Select Theme")
                    .setItems(themes, (dialog, which) -> {
                        prefs.setTheme(which);
                        tvTheme.setText(themes[which]);
                        Toast.makeText(getContext(), "Theme set to " + themes[which], Toast.LENGTH_SHORT).show();
                    }).show();
        });

        view.findViewById(R.id.setting_about).setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), AboutActivity.class));
        });

        view.findViewById(R.id.btn_edit_profile).setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), EditProfileActivity.class));
        });

        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            getActivity().finish();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh profile info when returning from EditProfileActivity
        TextView tvName = getView().findViewById(R.id.tv_profile_name);
        TextView tvEmail = getView().findViewById(R.id.tv_profile_email);
        tvName.setText(prefs.getUserName());
        tvEmail.setText(prefs.getUserEmail());
    }
}