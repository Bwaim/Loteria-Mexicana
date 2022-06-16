buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.hilt.gradle)
        classpath(libs.google.gms)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.firebase.perf.gradle)
    }
}

plugins {
    alias(libs.plugins.ben.manes.version)
    alias(libs.plugins.android.cache.fix)
}
