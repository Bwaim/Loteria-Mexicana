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
    id("loteriamexicana.android.library.compose")
    id("loteriamexicana.android.library.jacoco")
    id("loteriamexicana.spotless")
}

android {
    defaultConfig {
        testInstrumentationRunner = "dev.bwaim.loteria.test.android.HiltTestRunner"
    }
}

dependencies {
    implementation(project(":common:theme:theme"))

    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling.library)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.accompanist.insets.library)

    implementation(libs.timber)

    androidTestImplementation(project(":common:test:android"))
    androidTestImplementation(project(":common:theme:theme-impl"))
    androidTestImplementation(libs.junit.library)
    androidTestImplementation(libs.junit.params)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.test.truth)
    androidTestImplementation(libs.mockk.android)
}
