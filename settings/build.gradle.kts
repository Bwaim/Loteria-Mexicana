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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":common:compose"))
    implementation(project(":common:core"))
    implementation(project(":common:theme:theme"))

    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.accompanist.insets.ui)

    testImplementation(project(":common:test"))
    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.junit.library)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk.library)
    testImplementation(libs.cash.turbine)

    androidTestImplementation(libs.junit.library)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.test.truth)
    androidTestImplementation(libs.mockk.android)
}
