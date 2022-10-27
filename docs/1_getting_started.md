# Getting started

## [install gradle](https://docs.gradle.org/current/userguide/installation.html)

```shell
brew install gradle
```

## installing kotlin

1. [`brew install kotlin`](https://kotlinlang.org/docs/command-line.html) 통해서 설치 가능
2. 빌드툴 통해서 프로젝트별로 관리 가능
   - [gradle](https://kotlinlang.org/docs/gradle.html)

## convert to gradle project

```shell
gradle init \
  --type kotlin-application \
  --dsl kotlin
```
- 옵션
  - [여러 type](https://docs.gradle.org/current/userguide/build_init_plugin.html)의 init을 지원하며, 플러그인이 아닌 애플리케이션이 필요하므로 `kotlin application`으로 init
  - kotlin 빌드 스크립트 위해 `--dsl` 옵션은 kotlin 사용. ([`dsl`?](https://stackoverflow.com/a/48263925))
- `init`을 하면 자동으로 [gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)를 생성하게 된다

## Versions and dependencies managed as code

- 여러 빌드 스크립트로 빌드하는 [Composite builds](https://docs.gradle.org/current/userguide/composite_builds.html#composite_build_intro) 가능
- [`buildSrc` 디렉토리를 사용](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)하면 `included build` 처리 된다. 
  - [Better Dependency Management Using `buildSrc` + `Kotlin DSL`](https://proandroiddev.com/better-dependencies-management-using-buildsrc-kotlin-dsl-eda31cdb81bf) 
  - gradle이 `buildSrc` 디렉토리를 발견하면, 
    - 이를 자동으로 컴파일 및 테스트 하고,
    - 빌드 스크립트의 classpath에 넣는다
  - 단, [`buildSrc`를 사용 시 단점들](https://proandroiddev.com/stop-using-gradle-buildsrc-use-composite-builds-instead-3c38ac7a2ab3)이 있다고 하여, 필요할 때 `includeBuild`를 사용하는 것이 좋다고 한다

> A change in buildSrc causes the whole project to become out-of-date.  
> Thus, when making small incremental changes, the `--no-rebuild` command-line option is often helpful to get faster feedback.  
> Remember to run a full build regularly or at least when you’re done, though.

## application

`kotlin-application`을 선택하여 `init` 하면 [Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html)이 추가된다.