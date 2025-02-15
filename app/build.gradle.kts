plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.yisokheng.finalapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yisokheng.finalapp"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation ("androidx.compose.material:material-icons-extended:1.3.0")
    implementation ("androidx.compose.foundation:foundation:1.3.0" )// Or the latest version
    implementation ("androidx.compose.foundation:foundation-layout:1.3.0")
    implementation ("com.google.android.libraries.places:places:3.3.0")
    implementation ("com.google.maps.android:maps-compose:2.13.1")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation ("androidx.compose.material3:material3:1.1.2")
    implementation ("io.coil-kt:coil-compose:2.4.0")
    implementation ("com.google.accompanist:accompanist-pager:0.30.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation ("androidx.compose.material3:material3:1.0.0")
//    implementation("androidx.navigation:navigation-compose:2.7.5")
//    implementation ("com.google.android.libraries.places:places:3.3.0")
//    implementation ("com.google.maps.android:maps-compose:2.13.1")
//    implementation ("com.google.android.gms:play-services-maps:18.1.0")
//    implementation ("androidx.compose.material3:material3:1.1.2")
//    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")
//    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.6")
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation ("androidx.compose.foundation:foundation:1.5.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.generativeai)
    implementation(libs.androidx.core.splashscreen)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}