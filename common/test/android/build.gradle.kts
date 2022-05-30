plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.testing)
}
