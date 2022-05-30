plugins {
    id("loteriamexicana.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.core.library)
    implementation(libs.androidx.startup)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.firebase.crashlytics.library)

    implementation(libs.timber)
}
