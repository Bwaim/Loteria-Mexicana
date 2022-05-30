plugins {
    id("kotlin")
    id("loteriamexicana.spotless")
}

dependencies {
    implementation(libs.junit.library)
    implementation(libs.kotlin.coroutines.test)
}
