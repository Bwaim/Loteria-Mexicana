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
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("loteriamexicana.android.library")
    id("loteriamexicana.android.library.jacoco")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.protobuf)
    id("loteriamexicana.spotless")
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
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

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines.core)

    implementation(libs.protobuf.kotlin.lite)

    implementation(libs.androidx.datastore)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)

    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.junit.library)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk.library)
}
