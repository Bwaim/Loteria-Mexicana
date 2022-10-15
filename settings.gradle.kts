pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "loteriamexicana"

include(":app")

include(":common:card:card")
include(":common:card:card-db")
include(":common:card:card-impl")
include(":common:card-set:card-set-db")
include(":common:compose")
include(":common:core")
include(":common:coroutines:coroutines")
include(":common:coroutines:coroutines-android")
include(":common:database:common")
include(":common:database:database")
include(":common:http")
include(":common:imageloading")
include(":common:initializers")
include(":common:navigation")
include(":common:test:android")
include(":common:test:test")
include(":common:theme:theme")
include(":common:theme:theme-impl")
include(":common:ui-resources")

include(":features:draw")
include(":features:exampleFeature")
include(":features:settings")
