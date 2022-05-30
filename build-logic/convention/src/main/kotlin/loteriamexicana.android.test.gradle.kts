import dev.bwaim.loteria.configureKotlinAndroid

plugins {
    id("com.android.test")
    kotlin("android")
}

android {
    configureKotlinAndroid(this)

    defaultConfig {
        targetSdk = 31
    }
}
