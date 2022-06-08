
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class UnitTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("testImplementation", libs.findLibrary("kotlin.coroutines.test").get())
                add("testImplementation", libs.findLibrary("androidx.test.truth").get())
                add("testImplementation", libs.findLibrary("cash.turbine").get())
            }
        }
    }
}
