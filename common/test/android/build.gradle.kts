plugins {
    id("com.android.library")

    kotlin("android")
}

dependencies {
    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.testing)
}