plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        testInstrumentationRunner = "dev.bwaim.loteria.test.android.HiltTestRunner"
    }

    kapt {
        correctErrorTypes = true
    }
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
    add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

    add("implementation", libs.findLibrary("kotlin.coroutines.android").get())

    add("implementation", libs.findLibrary("hilt.library").get())
    add("kapt", libs.findLibrary("hilt.compiler").get())

    // TODO : Remove this dependency once we upgrade to Android Studio Dolphin b/228889042
    // These dependencies are currently necessary to render Compose previews
    add("debugImplementation", libs.findLibrary("androidx.customview.poolingcontainer").get())
}