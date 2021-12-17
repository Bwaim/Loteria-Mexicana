plugins {
    id("com.android.library")

    kotlin("android")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":common:theme:theme"))

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling.library)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.accompanist.insets.library)

    implementation(libs.timber)

    androidTestImplementation(project(":common:theme:theme-impl"))
    androidTestImplementation(libs.junit.library)
    androidTestImplementation(libs.junit.params)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.test.truth)
    androidTestImplementation(libs.mockk.android)
}
