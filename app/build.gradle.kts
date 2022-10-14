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
    id("loteriamexicana.android.application")
    id("loteriamexicana.android.application.compose")
    id("loteriamexicana.android.application.jacoco")
    id("loteriamexicana.android.test.compose")
    id("loteriamexicana.hilt")
    id("loteriamexicana.spotless")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}

android {
    namespace = "dev.bwaim.loteria.app"

    defaultConfig {
        applicationId = "dev.bwaim.loteria"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "dev.bwaim.loteria.test.android.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            extra.set("enableCrashlytics", false)

            applicationIdSuffix = ".debug"

            withGroovyBuilder {
                "FirebasePerformance" {
                    invokeMethod("setInstrumentationEnabled", false)
                }
            }
        }
        val release by getting {
            extra.set("enableCrashlytics", true)

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.common.card.card)
    implementation(projects.common.card.cardImpl)
    implementation(projects.common.compose)
    implementation(projects.common.coroutines.coroutines)
    implementation(projects.common.coroutines.coroutinesAndroid)
    implementation(projects.common.database.database)
    implementation(projects.common.http)
    implementation(projects.common.imageloading)
    implementation(projects.common.initializers)
    implementation(projects.common.navigation)
    implementation(projects.common.theme.theme)
    implementation(projects.common.theme.themeImpl)

    implementation(projects.features.draw)
    implementation(projects.features.settings)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.compose.animation)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.windowSizeClass)
    debugImplementation(libs.compose.ui.tooling.library)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.accompanist.insets.library)
    implementation(libs.accompanist.insets.ui)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.firebase.perf.library)

    // TODO deactivated till https://github.com/square/leakcanary/issues/2398 is solved
//    debugImplementation(libs.leakcanary.library)
//    implementation(libs.leakcanary.plumber)

    implementation(libs.coil.library)

    androidTestImplementation(project(":common:test:android"))
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.hilt.testing)
    kaptAndroidTest(libs.hilt.compiler)
}
