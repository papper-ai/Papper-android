// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
//    ext {
//        val compose_compiler = "1.4.3"
//        val compose_version = "1.3.3"
//        val compose_material_icons = "1.3.1"
//
//        val navigation_version = "2.7.7"
//        val coroutines_version = "1.7.3"
//        val retrofit_version = "2.9.0"
//        val okhttp_version = "4.12.0"
//        val picasso_version = "2.8"
//        val dagger2_version = "2.50"
//        val hilt_version = "2.51"
//        val hilt_navigation_compose_version = "1.0.0"
//        val hilt_compiler_version = "1.0.0"
//        val hilt_work_version = "1.0.0"
//        val orbit_version = "7.0.0"
//        val work_runtime_version = "2.7.1"
//        val kotlin_version = "1.8.10"
//    }
//
//    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
//    }
//    repositories {
//        mavenCentral()
//    }
//}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
    }
}

plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.2.1" apply false
    //id("com.google.dagger.hilt.android") version "2.51" apply false
}