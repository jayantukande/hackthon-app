package com.signbridgecommunication.app.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.data.db.SignBridgeDatabase;
import com.signbridgecommunication.app.ui.adapter.CategoryAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        RecyclerView rv = view.findViewById(R.id.rv_explore_categories);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        
        CategoryAdapter adapter = new CategoryAdapter(new ArrayList<>(), category -> {
            Toast.makeText(getContext(), "Selected: " + category.getName(), Toast.LENGTH_SHORT).show();
        });
        rv.setAdapter(adapter);

        SignBridgeDatabase.getDatabase(requireContext()).signDao().getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            adapter.setCategories(categories);
        });

        return view;
    }
}