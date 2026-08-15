package com.signbridgecommunication.app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.data.db.SignBridgeDatabase;
import com.signbridgecommunication.app.data.model.Category;
import com.signbridgecommunication.app.data.prefs.AppPreferences;
import com.signbridgecommunication.app.ui.adapter.CategoryAdapter;
import com.signbridgecommunication.app.ui.emergency.EmergencyActivity;

import java.util.ArrayList;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private AppPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        prefs = new AppPreferences(requireContext());
        TextView tvWelcome = view.findViewById(R.id.tv_welcome);
        tvWelcome.setText("Hello, " + prefs.getUserName() + " 👋");

        rvCategories = view.findViewById(R.id.rv_categories);
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new CategoryAdapter(new ArrayList<>(), category -> {
            Toast.makeText(getContext(), "Selected: " + category.getName(), Toast.LENGTH_SHORT).show();
        });
        rvCategories.setAdapter(adapter);

        view.findViewById(R.id.btn_emergency).setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), EmergencyActivity.class));
        });

        SignBridgeDatabase.getDatabase(requireContext()).signDao().getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            adapter.setCategories(categories);
        });
        
        return view;
    }
}