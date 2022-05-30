plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.android.feature")
    id("loteriamexicana.android.library.compose")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:card:card"))
    implementation(project(":common:compose"))
    implementation(project(":common:core"))
    implementation(project(":common:theme:theme"))

    implementation(libs.coil.library)
    implementation(libs.coil.compose)

    implementation(libs.compose.material)
}
