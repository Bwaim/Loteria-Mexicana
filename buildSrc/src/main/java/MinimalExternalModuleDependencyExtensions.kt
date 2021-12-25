import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider

fun Provider<MinimalExternalModuleDependency>.toArtifactSpec(): String {
    val externalModuleDependency = get()
    val module = externalModuleDependency.module
    val group = module.group
    val name = module.name
    val version = externalModuleDependency.versionConstraint.displayName
    return "${group}:${name}:${version}"
}