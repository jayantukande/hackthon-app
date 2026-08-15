plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.signbridgecommunication.app"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.signbridgecommunication.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.activity.ktx)
    
    // Lifecycle & ViewModel
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    
    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    
    // DataStore
    implementation(libs.datastore.preferences)
    implementation("androidx.datastore:datastore-rxjava3:1.1.1")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.1.1")
    
    // RxJava
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
