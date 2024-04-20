plugins {
    //kapt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    //modules
    implementation(project(":domain"))

    val hilt_version = "2.51"
    val retrofit_version = "2.9.0"
    val okhttp_version = "4.12.0"
    val logging_interceptor_version = "4.12.0"

    //Dagger-hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}