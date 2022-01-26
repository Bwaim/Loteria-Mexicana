plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":common:compose"))
    implementation(project(":common:coroutines:coroutines"))
    implementation(project(":common:core"))
    implementation(project(":common:theme:theme"))
    implementation(project(":common:ui-resources"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines.core)

    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt.library)

}
