plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.android.feature")
    id("loteriamexicana.android.library.compose")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:compose"))
    implementation(project(":common:core"))
    implementation(project(":common:theme:theme"))

    implementation(libs.compose.material)

    testImplementation(project(":common:test:test"))
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
