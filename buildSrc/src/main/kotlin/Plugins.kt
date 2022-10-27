import org.gradle.api.plugins.PluginAware
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec

// https://docs.gradle.org/current/userguide/plugins.html#sec:subprojects_plugins_dsl
// multi project 빌드 시 일부 또는 전체 서브 프로젝트에 플러그인 적용을 원하고 루트 프로젝트에 적용하는 것은 원하지 않을 때,
// `apply`를 `false`로 설정하고, 따로 apply 한다
val PluginDependenciesSpec.kotlinJvm get() =
    kotlin("jvm") version Versions.KOTLIN apply false
const val KOTLIN_JVM_PLUGIN = "org.jetbrains.kotlin.jvm"
fun PluginAware.applyKotlinJvm() {
    apply(plugin = KOTLIN_JVM_PLUGIN)
}

