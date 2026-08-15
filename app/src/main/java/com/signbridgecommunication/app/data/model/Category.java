package com.signbridgecommunication.app.data.model;

public class Category {
    private final String id;
    private final String name;
    private final int iconResId;
    private final int phraseCount;

    public Category(String id, String name, int iconResId, int phraseCount) {
        this.id = id;
        this.name = name;
        this.iconResId = iconResId;
        this.phraseCount = phraseCount;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getIconResId() { return iconResId; }
    public int getPhraseCount() { return phraseCount; }
}
