package com.signbridgecommunication.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.signbridgecommunication.app.data.datastore.OnboardingManager;
import com.signbridgecommunication.app.data.repository.SignBridgeRepository;
import com.signbridgecommunication.app.databinding.ActivityMainBinding;
import com.signbridgecommunication.app.ui.activities.OnboardingActivity;
import com.signbridgecommunication.app.viewmodel.MainViewModel;
import com.signbridgecommunication.app.viewmodel.ViewModelFactory;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private Disposable onboardingDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        OnboardingManager onboardingManager = new OnboardingManager(this);
        onboardingDisposable = onboardingManager.isOnboardingCompleted()
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(completed -> {
                if (!completed) {
                    startActivity(new Intent(this, OnboardingActivity.class));
                    finish();
                } else {
                    initUI();
                }
            });
    }

    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        SignBridgeRepository repository = new SignBridgeRepository();
        OnboardingManager onboardingManager = new OnboardingManager(this);
        ViewModelFactory factory = new ViewModelFactory(repository, onboardingManager);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        viewModel.getStats().observe(this, stats -> {
            // Update stats UI
        });

        viewModel.getCategories().observe(this, categories -> {
            // Update categories UI
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (onboardingDisposable != null) onboardingDisposable.dispose();
    }
}
