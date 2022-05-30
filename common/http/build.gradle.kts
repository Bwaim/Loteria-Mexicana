plugins {
    id("loteriamexicana.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.core.library)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.okhttp.library)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit.library)
    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.mockk.library)

    androidTestImplementation(libs.junit.library)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.truth)
}
