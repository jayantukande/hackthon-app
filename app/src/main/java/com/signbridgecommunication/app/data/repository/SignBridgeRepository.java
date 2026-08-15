package com.signbridgecommunication.app.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.signbridgecommunication.app.data.dao.SignDao;
import com.signbridgecommunication.app.data.model.Category;
import com.signbridgecommunication.app.data.model.Phrase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignBridgeRepository {
    private final SignDao signDao;

    public SignBridgeRepository(SignDao signDao) {
        this.signDao = signDao;
    }

    public LiveData<List<Category>> getCategories() {
        return signDao.getAllCategories();
    }

    public LiveData<List<Phrase>> getFavoritePhrases() {
        return signDao.getFavoritePhrases();
    }

    public LiveData<List<Phrase>> getRecentPhrases() {
        return signDao.getRecentPhrases();
    }

    public LiveData<List<Phrase>> searchPhrases(String query) {
        return signDao.searchPhrases(query);
    }

    public LiveData<Map<String, Integer>> getStats() {
        MediatorLiveData<Map<String, Integer>> statsMap = new MediatorLiveData<>();
        Map<String, Integer> currentStats = new HashMap<>();

        LiveData<Integer> learnedCount = signDao.getLearnedPhrasesCount();
        LiveData<Integer> favoriteCount = signDao.getFavoritePhrasesCount();
        LiveData<Integer> recentCount = signDao.getLearnedPhrasesCount(); // Using same for now as proxy
        LiveData<Integer> categoryCount = signDao.getCategoriesCount();

        statsMap.addSource(learnedCount, count -> {
            currentStats.put("Signs Learned", count);
            statsMap.setValue(currentStats);
        });
        statsMap.addSource(favoriteCount, count -> {
            currentStats.put("Favorite Phrases", count);
            statsMap.setValue(currentStats);
        });
        statsMap.addSource(categoryCount, count -> {
            currentStats.put("Categories Explored", count);
            statsMap.setValue(currentStats);
        });
        // "Recently Used" can be same as learned for this hackathon mockup or count of non-zero lastUsed
        currentStats.put("Recently Used", 0); 

        return statsMap;
    }
    
    public void updatePhrase(Phrase phrase) {
        signDao.updatePhrase(phrase);
    }
}
