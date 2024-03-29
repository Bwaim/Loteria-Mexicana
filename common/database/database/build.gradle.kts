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
    id("loteriamexicana.android.test")
    id("loteriamexicana.hilt")
    alias(libs.plugins.ksp)
    id("loteriamexicana.spotless")
}

android {
    namespace = "dev.bwaim.loteria.database"

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(projects.common.card.card)
    implementation(projects.common.card.cardDb)
    implementation(projects.common.cardSet.cardSetDb)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
