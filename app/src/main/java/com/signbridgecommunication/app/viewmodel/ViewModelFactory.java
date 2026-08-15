package com.signbridgecommunication.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.signbridgecommunication.app.data.datastore.OnboardingManager;
import com.signbridgecommunication.app.data.repository.SignBridgeRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final SignBridgeRepository repository;
    private final OnboardingManager onboardingManager;

    public ViewModelFactory(SignBridgeRepository repository, OnboardingManager onboardingManager) {
        this.repository = repository;
        this.onboardingManager = onboardingManager;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository, onboardingManager);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
