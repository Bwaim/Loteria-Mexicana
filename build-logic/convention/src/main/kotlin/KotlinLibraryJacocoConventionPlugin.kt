import dev.bwaim.loteria.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("org.gradle.jacoco")
                configureJacoco()
            }
        }
    }
}
