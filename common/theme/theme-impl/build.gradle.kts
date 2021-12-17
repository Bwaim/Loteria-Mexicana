import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")

    id("com.google.protobuf")
}

android {
    kapt {
        correctErrorTypes = true
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.toArtifactSpec()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
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

    implementation(libs.protobuf.javalite)

    implementation(libs.androidx.datastore)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)

    testImplementation(libs.androidx.test.truth)
    testImplementation(libs.junit.library)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk.library)
}
