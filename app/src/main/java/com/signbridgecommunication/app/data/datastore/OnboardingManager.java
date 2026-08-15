package com.signbridgecommunication.app.data.datastore;

import android.content.Context;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class OnboardingManager {
    private final RxDataStore<Preferences> dataStore;
    private static final Preferences.Key<Boolean> IS_ONBOARDING_COMPLETED = PreferencesKeys.booleanKey("is_onboarding_completed");

    public OnboardingManager(Context context) {
        dataStore = new RxPreferenceDataStoreBuilder(context, "onboarding_prefs").build();
    }

    public Flowable<Boolean> isOnboardingCompleted() {
        return dataStore.data().map(prefs -> prefs.get(IS_ONBOARDING_COMPLETED) != null && prefs.get(IS_ONBOARDING_COMPLETED));
    }

    public void setOnboardingCompleted(boolean completed) {
        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(IS_ONBOARDING_COMPLETED, completed);
            return Single.just(mutablePreferences);
        });
    }
}
