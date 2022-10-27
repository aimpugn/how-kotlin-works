plugins {
    java
    kotlinJvm
}

allprojects {
    // https://stackoverflow.com/a/53679150
    // plugin 블록에서 apply false 하고, 각 프로젝트마다 apply를 한다
    // apply 않을 경우 다음과 같은 에러 발생
    // => Could not find or load main class how.kotlin.works.AppKt
    applyKotlinJvm()
}