import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun KotlinJvmOptions.context() {
    freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
}
