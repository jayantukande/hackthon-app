package com.signbridgecommunication.app.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.signbridgecommunication.app.data.model.Category;
import com.signbridgecommunication.app.data.model.Phrase;

import java.util.List;

@Dao
public interface SignDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(List<Category> categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhrases(List<Phrase> phrases);

    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM phrases WHERE categoryId = :categoryId")
    LiveData<List<Phrase>> getPhrasesByCategory(int categoryId);

    @Query("SELECT * FROM phrases WHERE isFavorite = 1")
    LiveData<List<Phrase>> getFavoritePhrases();

    @Query("SELECT * FROM phrases WHERE englishText LIKE '%' || :query || '%' OR hindiText LIKE '%' || :query || '%' OR marathiText LIKE '%' || :query || '%'")
    LiveData<List<Phrase>> searchPhrases(String query);

    @Query("SELECT * FROM phrases ORDER BY lastUsed DESC LIMIT 10")
    LiveData<List<Phrase>> getRecentPhrases();

    @Update
    void updatePhrase(Phrase phrase);

    @Query("SELECT * FROM phrases WHERE id = :id")
    Phrase getPhraseById(int id);

    @Query("SELECT COUNT(*) FROM phrases")
    LiveData<Integer> getTotalPhrasesCount();

    @Query("SELECT COUNT(*) FROM phrases WHERE lastUsed > 0")
    LiveData<Integer> getLearnedPhrasesCount();

    @Query("SELECT COUNT(*) FROM phrases WHERE isFavorite = 1")
    LiveData<Integer> getFavoritePhrasesCount();

    @Query("SELECT COUNT(*) FROM categories")
    LiveData<Integer> getCategoriesCount();
}