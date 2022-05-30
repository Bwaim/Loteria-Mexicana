plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.android.feature")
    id("loteriamexicana.android.library.compose")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:compose"))

    implementation(libs.compose.material)
}
