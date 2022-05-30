plugins {
    id("kotlin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(libs.dagger)

    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.stdlib)
}
