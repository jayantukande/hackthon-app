package com.signbridgecommunication.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.signbridgecommunication.app.MainActivity;
import com.signbridgecommunication.app.data.datastore.OnboardingManager;
import com.signbridgecommunication.app.databinding.ActivityOnboardingBinding;
// import com.signbridgecommunication.app.ui.adapters.OnboardingAdapter;

public class OnboardingActivity extends AppCompatActivity {
    private ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // OnboardingAdapter adapter = new OnboardingAdapter();
        // binding.viewPager.setAdapter(adapter);

        OnboardingManager onboardingManager = new OnboardingManager(this);

        binding.btnNext.setOnClickListener(v -> {
            int current = binding.viewPager.getCurrentItem();
            // if (current < adapter.getItemCount() - 1) {
            //    binding.viewPager.setCurrentItem(current + 1);
            // } else {
                onboardingManager.setOnboardingCompleted(true);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            // }
        });

        binding.btnSkip.setOnClickListener(v -> {
            onboardingManager.setOnboardingCompleted(true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
