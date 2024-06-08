plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.cal.kotlinquizappproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cal.kotlinquizappproject"
        minSdk = 26
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

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Chip Navigation Bar
    implementation ("com.github.ismaeldivita:chip-navigation-bar:1.4.0")

    // Glide Image Loader
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // Circular Image View
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // TextView Animation
    implementation ("com.daimajia.easing:library:2.0@aar")
    implementation ("com.daimajia.androidanimations:library:2.3@aar")
}