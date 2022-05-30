plugins {
    id("kotlin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(project(":common:coroutines:coroutines"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines.core)

    implementation(libs.dagger)
}
