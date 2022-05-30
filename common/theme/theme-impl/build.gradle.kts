import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("loteriamexicana.android.library")
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
