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

// TODO: Remove once https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.android.library.jacoco")
    id("loteriamexicana.hilt")
    alias(libs.plugins.protobuf)
    id("loteriamexicana.spotless")
    id("loteriamexicana.test")
}

android {
    namespace = "dev.bwaim.loteria.theme.impl"

    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    // TODO remove this when protobuf version > 3.21.7
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xexplicit-api=warning"
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().configureEach {
            builtins {
                val java by registering {
                    option("lite")
                }
                val kotlin by registering {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:coroutines:coroutines"))
    implementation(project(":common:theme:theme"))

    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)

    testImplementation(project(":common:test:android"))
    testImplementation(project(":common:test:test"))
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk.library)
}
