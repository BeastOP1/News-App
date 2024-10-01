plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("kotlin-parcelize")

}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //
    implementation(libs.androidx.datastore.core.android)
    //
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    implementation("androidx.compose.material:material-icons-extended-android:1.6.8")
    //Splash api
    implementation("androidx.core:core-splashscreen:1.0.1")
    ///retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //HIlt


    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    //Compose Navigation
    val nav_version = "2.6.0"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Coil
    implementation("io.coil-kt:coil-compose:2.7.0")
    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    //compose foundation
//    implementation("androidx.wear.compose:compose-foundation:1.3.1")

    //Paging 3
    val paging_version = "3.1.1"
    implementation("androidx.paging:paging-runtime:$paging_version")
    implementation("androidx.paging:paging-compose:3.2.0-rc01")
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")

    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

}