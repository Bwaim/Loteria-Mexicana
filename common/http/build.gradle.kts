/*
 * Copyright 2022 Dev Bwaim team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.android.library.jacoco")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("loteriamexicana.spotless")
}

android {
    defaultConfig {
        testInstrumentationRunner = "dev.bwaim.loteria.test.android.HiltTestRunner"
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.core.library)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    implementation(libs.okhttp.library)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit.library)
    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.mockk.library)

    androidTestImplementation(project(":common:test:android"))
    androidTestImplementation(libs.junit.library)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.truth)
}
