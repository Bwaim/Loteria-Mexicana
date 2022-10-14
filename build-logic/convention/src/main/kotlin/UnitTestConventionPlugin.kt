
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class UnitTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("testImplementation", project(":common:test:test"))
                add("testImplementation", libs.findLibrary("junit-library").get())
                add("testImplementation", libs.findLibrary("kotlin.coroutines.test").get())
                add("testImplementation", libs.findLibrary("cash.turbine").get())
            }
        }
    }
}
