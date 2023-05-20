
import com.android.build.api.variant.TestAndroidComponentsExtension
import com.android.build.gradle.TestExtension
import dev.bwaim.loteria.configureGradleManagedDevices
import dev.bwaim.loteria.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class BenchmarkConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
                configureGradleManagedDevices(this)

                defaultConfig {
                    testInstrumentationRunner = "dev.bwaim.loteria.test.android.HiltTestRunner"
                }

                buildTypes {
                    // This benchmark buildType is used for benchmarking, and should function like your
                    // release build (for example, with minification on). It"s signed with a debug key
                    // for easy local/CI testing.
                    create("benchmark") {
                        isDebuggable = true
                        signingConfig = getByName("debug").signingConfig
                        matchingFallbacks += listOf("release")
                    }
                }

                targetProjectPath = ":app"
                experimentalProperties["android.experimental.self-instrumenting"] = true
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":common:test:android"))
                add("implementation", libs.findLibrary("androidx-test-espresso-core").get())
                add("implementation", libs.findLibrary("androidx-test-uiautomator").get())
                add("implementation", libs.findLibrary("androidx-benchmark-macro").get())
            }

            extensions.configure<TestAndroidComponentsExtension> {
                beforeVariants(selector().all()) {
                    it.enable = it.buildType == "benchmark"
                }
            }
        }
    }
}
