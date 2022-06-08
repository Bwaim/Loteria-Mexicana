plugins {
    `kotlin-dsl`
}

group = "dev.bwaim.loteria.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "loteriamexicana.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "loteriamexicana.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "loteriamexicana.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "loteriamexicana.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "loteriamexicana.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "loteriamexicana.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "loteriamexicana.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("androidTest") {
            id = "loteriamexicana.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("androidTestCompose") {
            id = "loteriamexicana.android.test.compose"
            implementationClass = "AndroidTestComposeConventionPlugin"
        }
        register("hilt") {
            id = "loteriamexicana.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("kotlin") {
            id = "loteriamexicana.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kotlinJacoco") {
            id = "loteriamexicana.kotlin.library.jacoco"
            implementationClass = "KotlinLibraryJacocoConventionPlugin"
        }
        register("spotless") {
            id = "loteriamexicana.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("unitTest") {
            id = "loteriamexicana.test"
            implementationClass = "UnitTestConventionPlugin"
        }
    }
}
