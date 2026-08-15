package com.signbridgecommunication.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.signbridgecommunication.app.data.model.Category;
import com.signbridgecommunication.app.data.model.Phrase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SignBridgeRepository {
    private final List<Category> mockCategories = new ArrayList<>();
    private final List<Phrase> mockPhrases = new ArrayList<>();

    public SignBridgeRepository() {
        mockCategories.add(new Category("1", "Basic Communication", 0, 12));
        mockCategories.add(new Category("2", "Emergency", 0, 15));
        mockCategories.add(new Category("3", "Hospital", 0, 18));
        mockCategories.add(new Category("4", "School", 0, 13));
        mockCategories.add(new Category("5", "Workplace", 0, 20));
        mockCategories.add(new Category("6", "Travel", 0, 16));
        mockCategories.add(new Category("7", "Shopping", 0, 22));
        mockCategories.add(new Category("8", "Food", 0, 25));
        mockCategories.add(new Category("9", "Social", 0, 30));

        mockPhrases.add(new Phrase("1", "Hello", "Basic Communication", true, System.currentTimeMillis() - 1000, 5));
        mockPhrases.add(new Phrase("2", "Thank you", "Basic Communication", false, System.currentTimeMillis() - 5000, 3));
        mockPhrases.add(new Phrase("3", "I need help", "Emergency", true, System.currentTimeMillis() - 10000, 10));
        mockPhrases.add(new Phrase("4", "Where is the hospital?", "Hospital", false, 0, 0));
    }

    public LiveData<List<Category>> getCategories() {
        MutableLiveData<List<Category>> data = new MutableLiveData<>();
        data.setValue(mockCategories);
        return data;
    }

    public LiveData<List<Phrase>> getFavoritePhrases() {
        MutableLiveData<List<Phrase>> data = new MutableLiveData<>();
        data.setValue(mockPhrases.stream().filter(Phrase::isFavorite).collect(Collectors.toList()));
        return data;
    }

    public LiveData<List<Phrase>> getRecentPhrases() {
        MutableLiveData<List<Phrase>> data = new MutableLiveData<>();
        data.setValue(mockPhrases.stream()
                .filter(p -> p.getLastUsedTimestamp() > 0)
                .sorted((p1, p2) -> Long.compare(p2.getLastUsedTimestamp(), p1.getLastUsedTimestamp()))
                .collect(Collectors.toList()));
        return data;
    }

    public LiveData<Map<String, Integer>> getStats() {
        MutableLiveData<Map<String, Integer>> data = new MutableLiveData<>();
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Signs Learned", mockPhrases.stream().mapToInt(Phrase::getLearnCount).sum());
        stats.put("Favorite Phrases", (int) mockPhrases.stream().filter(Phrase::isFavorite).count());
        stats.put("Recently Used", (int) mockPhrases.stream().filter(p -> p.getLastUsedTimestamp() > 0).count());
        stats.put("Categories Explored", mockCategories.size());
        data.setValue(stats);
        return data;
    }
}
