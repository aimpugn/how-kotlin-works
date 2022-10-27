# How to run

## by gradle wrapper

호스트에 설치된 gradle 대신 [gradlew wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) 사용해서 원하는 gradle 버전을 다운받고 실행하는 등 빌드 툴 관련 작업을 gradle에 맡길 수 있다.

```shell
./gradlew :app:run
```

### tasks

`gradle`은 `Tasks` 통해서 작업이 이뤄지며, gradle 명령어 통해서 가능한 tasks 조회 가능하다.

<details>
<summary>루트 디렉토리에서 ./gradlew tasks 예제 1</summary>

```shell
./gradlew tasks   

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project 'how-kotlin-works'
------------------------------------------------------------

Application tasks
-----------------
run - Runs this project as a JVM application

Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildKotlinToolingMetadata - Build metadata json file containing information about the used Kotlin tooling
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Distribution tasks
------------------
assembleDist - Assembles the main distributions
distTar - Bundles the project as a distribution.
distZip - Bundles the project as a distribution.
installDist - Installs the project as a distribution as-is.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'how-kotlin-works'.
dependencies - Displays all dependencies declared in root project 'how-kotlin-works'.
dependencyInsight - Displays the insight into a specific dependency in root project 'how-kotlin-works'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
kotlinDslAccessorsReport - Prints the Kotlin code for accessing the currently available project extensions and conventions.
outgoingVariants - Displays the outgoing variants of root project 'how-kotlin-works'.
projects - Displays the sub-projects of root project 'how-kotlin-works'.
properties - Displays the properties of root project 'how-kotlin-works'.
resolvableConfigurations - Displays the configurations that can be resolved in root project 'how-kotlin-works'.
tasks - Displays the tasks runnable from root project 'how-kotlin-works' (some of the displayed tasks may belong to subprojects).

Verification tasks
------------------
check - Runs all checks.
test - Runs the test suite.

Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.

To see all tasks and more detail, run gradlew tasks --all

To see more detail about a task, run gradlew help --task <task>

BUILD SUCCESSFUL in 668ms
7 actionable tasks: 1 executed, 6 up-to-date
```
</details>

또한 각 서브 프로젝트별로 tasks가 있으며, 별도로 조회가 가능하다

<details>
<summary>./gradlew :app:tasks</summary>

```shell
./gradlew :app:tasks

> Task :app:tasks

------------------------------------------------------------
Tasks runnable from project ':app'
------------------------------------------------------------

Application tasks
-----------------
run - Runs this project as a JVM application

Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildKotlinToolingMetadata - Build metadata json file containing information about the used Kotlin tooling
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Distribution tasks
------------------
assembleDist - Assembles the main distributions
distTar - Bundles the project as a distribution.
distZip - Bundles the project as a distribution.
installDist - Installs the project as a distribution as-is.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in project ':app'.
dependencies - Displays all dependencies declared in project ':app'.
dependencyInsight - Displays the insight into a specific dependency in project ':app'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
kotlinDslAccessorsReport - Prints the Kotlin code for accessing the currently available project extensions and conventions.
outgoingVariants - Displays the outgoing variants of project ':app'.
projects - Displays the sub-projects of project ':app'.
properties - Displays the properties of project ':app'.
resolvableConfigurations - Displays the configurations that can be resolved in project ':app'.
tasks - Displays the tasks runnable from project ':app'.

Verification tasks
------------------
check - Runs all checks.
test - Runs the test suite.

Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.

To see all tasks and more detail, run gradlew tasks --all

To see more detail about a task, run gradlew help --task <task>

BUILD SUCCESSFUL in 484ms
7 actionable tasks: 1 executed, 6 up-to-date
```
</details>

### pass args to main function

`gradle >= 4.9`부터 지원하는 `--args` 옵션을 사용해서 넘길 수 있고, 공백으로 구분된다.

```kotlin
fun main(args: Array<String>) {
    println("when main called")
    args.forEach {
        println(it)
    }
}
```

```shell
./gradlew :app:run --args='k1=v1 k2=v2 --k3=v3 k4=v4k5=v5'

> Task :app:run
when main called
k1=v1
k2=v2
--k3=v3
k4=v4k5=v5

BUILD SUCCESSFUL in 484ms
10 actionable tasks: 1 executed, 9 up-to-date
```

### JVM options

`applicationDefaultJvmArgs` 속성을 사용하여 JVM 옵션 전달할 수 있다.


## by java

IntelliJ 통해서 실행할 경우에는 아래와 같이

1. Java 메서드 호출을 캡처하기 위한 `javaagent`를 지정하고,
    - [`javaagent`?](https://docs.oracle.com/en/java/javase/17/docs/api/java.instrument/java/lang/instrument/package-summary.html)
2. `classpath`를 지정하며,
3. 마지막에 실행될 패키지의 클래스명을 지정하고 있다

```shell
/path/to/home/Library/Java/JavaVirtualMachines/temurin-17.0.4.1/Contents/Home/bin/java \
    -javaagent:/path/to/home/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/222.4345.14/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=64333:/path/to/home/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/222.4345.14/IntelliJ IDEA.app/Contents/bin \
    -Dfile.encoding=UTF-8 \
    -classpath /path/to/home/IdeaProjects/how-kotlin-works/app/build/classes/kotlin/main:/path/to/home/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.7.20/eac6656981d9d7156e838467d2d8d79093b1570/kotlin-stdlib-jdk8-1.7.20.jar:/path/to/home/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.7.20/2a729aa8763306368e665e2b747abd1dfd29b9d5/kotlin-stdlib-jdk7-1.7.20.jar:/path/to/home/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.7.20/726594ea9ba2beb2ee113647fefa9a10f9fabe52/kotlin-stdlib-1.7.20.jar:/path/to/home/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.7.20/e15351bdaf9fa06f009be5da7a202e4184f00ae3/kotlin-stdlib-common-1.7.20.jar:/path/to/home/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar \
    how.kot.works.AppKt
```