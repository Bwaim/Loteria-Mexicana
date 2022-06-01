import dev.bwaim.loteria.configureJacoco

plugins {
    id("com.android.library")
    jacoco
}

android {
    androidComponents {
        configureJacoco(this)
    }
}
