package com.signbridgecommunication.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.signbridgecommunication.app.data.datastore.OnboardingManager;
import com.signbridgecommunication.app.data.model.Category;
import com.signbridgecommunication.app.data.model.Phrase;
import com.signbridgecommunication.app.data.repository.SignBridgeRepository;
import java.util.List;
import java.util.Map;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class MainViewModel extends ViewModel {
    private final SignBridgeRepository repository;
    private final OnboardingManager onboardingManager;
    private final MutableLiveData<Boolean> isOnboardingCompleted = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MainViewModel(SignBridgeRepository repository, OnboardingManager onboardingManager) {
        this.repository = repository;
        this.onboardingManager = onboardingManager;
        
        disposables.add(onboardingManager.isOnboardingCompleted()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(isOnboardingCompleted::setValue));
    }

    public LiveData<Boolean> isOnboardingCompleted() {
        return isOnboardingCompleted;
    }

    public LiveData<List<Category>> getCategories() {
        return repository.getCategories();
    }

    public LiveData<List<Phrase>> getFavoritePhrases() {
        return repository.getFavoritePhrases();
    }

    public LiveData<List<Phrase>> getRecentPhrases() {
        return repository.getRecentPhrases();
    }

    public LiveData<Map<String, Integer>> getStats() {
        return repository.getStats();
    }

    public void completeOnboarding() {
        onboardingManager.setOnboardingCompleted(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
