plugins {
    id("loteriamexicana.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    implementation(libs.androidx.core.library)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    api(libs.coil.library)
}
