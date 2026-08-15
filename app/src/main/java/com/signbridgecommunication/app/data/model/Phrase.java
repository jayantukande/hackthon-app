package com.signbridgecommunication.app.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phrases")
public class Phrase {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int categoryId;
    private String englishText;
    private String hindiText;
    private String marathiText;
    private String signImageUrl; // resource name or local path
    private String description;
    private boolean isFavorite;
    private long lastUsed;

    public Phrase(int categoryId, String englishText, String hindiText, String marathiText, String signImageUrl, String description) {
        this.categoryId = categoryId;
        this.englishText = englishText;
        this.hindiText = hindiText;
        this.marathiText = marathiText;
        this.signImageUrl = signImageUrl;
        this.description = description;
        this.isFavorite = false;
        this.lastUsed = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getEnglishText() { return englishText; }
    public void setEnglishText(String englishText) { this.englishText = englishText; }

    public String getHindiText() { return hindiText; }
    public void setHindiText(String hindiText) { this.hindiText = hindiText; }

    public String getMarathiText() { return marathiText; }
    public void setMarathiText(String marathiText) { this.marathiText = marathiText; }

    public String getSignImageUrl() { return signImageUrl; }
    public void setSignImageUrl(String signImageUrl) { this.signImageUrl = signImageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public long getLastUsed() { return lastUsed; }
    public void setLastUsed(long lastUsed) { this.lastUsed = lastUsed; }
}