plugins {
    id("loteriamexicana.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)

    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.stdlib)
}
