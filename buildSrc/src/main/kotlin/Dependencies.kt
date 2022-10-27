import org.gradle.kotlin.dsl.DependencyHandlerScope

/* kotlin related versions */
/**
 * Align versions of all Kotlin components
 */
val DependencyHandlerScope.KOTLIN_BOM get() =
    platform("org.jetbrains.kotlin:kotlin-bom:${Versions.KOTLIN}")
/**
 * Use the Kotlin JDK 8 standard library.
 */
val DependencyHandlerScope.KOTLIN_STDLIB_JDK8 get() =
    platform("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}")
/**
 * Use the Kotlin test library.
 */
val DependencyHandlerScope.KOTLIN_TEST get() =
    platform("org.jetbrains.kotlin:kotlin-test:${Versions.KOTLIN}")
/**
 * Use the Kotlin JUnit integration.
 */
val DependencyHandlerScope.KOTLIN_TEST_JUNIT get() =
    platform("org.jetbrains.kotlin:kotlin-test-junit:${Versions.KOTLIN}")


/**
 * This dependency is used by the application.
 */
val DependencyHandlerScope.GUAVA get() =
    platform("com.google.guava:guava:31.1-jre")