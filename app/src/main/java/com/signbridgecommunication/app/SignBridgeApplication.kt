package com.signbridgecommunication.app

import android.app.Application
import com.signbridgecommunication.app.data.datastore.OnboardingDataStore
import com.signbridgecommunication.app.data.repository.SignBridgeRepository

class SignBridgeApplication : Application() {
    val repository by lazy { SignBridgeRepository() }
    val onboardingDataStore by lazy { OnboardingDataStore(this) }
}
