plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")
}

android {
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:coroutines:coroutines"))
    implementation(project(":common:card:card"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines.core)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)
}
