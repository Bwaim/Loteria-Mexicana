
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "dev.bwaim.loteria.test.android.HiltTestRunner"
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("androidTestImplementation", project(":common:test:android"))
                add("androidTestImplementation", libs.findLibrary("kotlin.coroutines.test").get())
                add("androidTestImplementation", libs.findLibrary("junit-library").get())
                // force upgrade to 1.1.0 because its required by androidTestImplementation,
                // and without this statement AGP will silently downgrade to tracing:1.0.0
                add("implementation", libs.findLibrary("androidx-tracing").get())
            }
        }
    }
}
