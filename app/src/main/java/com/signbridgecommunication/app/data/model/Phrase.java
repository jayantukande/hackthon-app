package com.signbridgecommunication.app.data.model;

public class Phrase {
    private final String id;
    private final String text;
    private final String category;
    private boolean isFavorite;
    private long lastUsedTimestamp;
    private int learnCount;

    public Phrase(String id, String text, String category, boolean isFavorite, long lastUsedTimestamp, int learnCount) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.isFavorite = isFavorite;
        this.lastUsedTimestamp = lastUsedTimestamp;
        this.learnCount = learnCount;
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public String getCategory() { return category; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public long getLastUsedTimestamp() { return lastUsedTimestamp; }
    public void setLastUsedTimestamp(long lastUsedTimestamp) { this.lastUsedTimestamp = lastUsedTimestamp; }
    public int getLearnCount() { return learnCount; }
    public void setLearnCount(int learnCount) { this.learnCount = learnCount; }
}
