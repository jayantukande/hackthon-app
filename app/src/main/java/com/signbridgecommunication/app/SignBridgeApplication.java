package com.signbridgecommunication.app;

import android.app.Application;
import com.signbridgecommunication.app.data.db.SignBridgeDatabase;
import com.signbridgecommunication.app.data.repository.SignBridgeRepository;
import com.signbridgecommunication.app.data.datastore.OnboardingManager;

public class SignBridgeApplication extends Application {
    private SignBridgeRepository repository;
    private OnboardingManager onboardingManager;

    @Override
    public void onCreate() {
        super.onCreate();
        SignBridgeDatabase database = SignBridgeDatabase.getDatabase(this);
        repository = new SignBridgeRepository(database.signDao());
        onboardingManager = new OnboardingManager(this);
    }

    public SignBridgeRepository getRepository() {
        return repository;
    }

    public OnboardingManager getOnboardingManager() {
        return onboardingManager;
    }
}
