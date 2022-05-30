plugins {
    id("loteriamexicana.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
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
