package com.signbridgecommunication.app.ui.favorites;

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
import com.signbridgecommunication.app.data.model.Phrase;
import com.signbridgecommunication.app.ui.adapter.PhraseAdapter;
import com.signbridgecommunication.app.ui.detail.SignDetailActivity;

import java.util.ArrayList;
import android.content.Intent;

public class FavoritesFragment extends Fragment {

    private RecyclerView rv;
    private PhraseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        rv = view.findViewById(R.id.rv_favorites);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new PhraseAdapter(new ArrayList<>(), new PhraseAdapter.OnPhraseClickListener() {
            @Override
            public void onPhraseClick(Phrase phrase) {
                Intent intent = new Intent(requireContext(), SignDetailActivity.class);
                intent.putExtra("PHRASE_ID", phrase.getId());
                startActivity(intent);
            }

            @Override
            public void onFavoriteClick(Phrase phrase) {
                phrase.setFavorite(!phrase.isFavorite());
                SignBridgeDatabase.databaseWriteExecutor.execute(() -> {
                    SignBridgeDatabase.getDatabase(requireContext()).signDao().updatePhrase(phrase);
                });
            }
        });
        rv.setAdapter(adapter);

        SignBridgeDatabase.getDatabase(requireContext()).signDao().getFavoritePhrases().observe(getViewLifecycleOwner(), phrases -> {
            adapter.setPhrases(phrases);
        });
        
        return view;
    }
}