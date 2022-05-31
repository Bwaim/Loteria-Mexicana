plugins {
    `kotlin-dsl`
}

group = "dev.bwaim.loteria.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.spotless.gradlePlugin)
}
