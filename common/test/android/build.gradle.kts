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
    id("loteriamexicana.hilt")
    id("loteriamexicana.spotless")
}

android {
    namespace = "dev.bwaim.loteria.test.android"
}

dependencies {
    implementation(projects.common.coroutines.coroutines)
    implementation(projects.common.coroutines.coroutinesAndroid)
    implementation(projects.common.database.database)
    implementation(projects.common.locale.localeImpl)
    implementation(projects.common.theme.themeImpl)
    implementation(projects.common.test.test)

    implementation(libs.kotlin.coroutines.test)

    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.testing)

    implementation(libs.androidx.datastore)

    implementation(libs.room.runtime)
}
