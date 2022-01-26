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

    implementation(libs.coil.library)
    implementation(libs.coil.compose)

    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt.library)
    implementation(project(mapOf("path" to ":common:card:card")))
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.accompanist.insets.ui)
}
