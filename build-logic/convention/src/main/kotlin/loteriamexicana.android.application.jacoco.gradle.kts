import dev.bwaim.loteria.configureJacoco

plugins {
    id("com.android.application")
    jacoco
}

android {
    androidComponents {
        configureJacoco(this)
    }
}
