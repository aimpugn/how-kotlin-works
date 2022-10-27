# kotlin dsl

## [Gradle Kotlin SDL Primer](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

### [Applying plugins with the plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block)

### [Applying external plugins with same version to subprojects](https://docs.gradle.org/current/userguide/plugins.html#sec:subprojects_plugins_dsl)

## [Improving the Performance of Gradle Builds](https://docs.gradle.org/current/userguide/performance.html#performance_gradle)

## build

### plugins and apply


> This issue isn't specific to Kotlin, and is due to a race condition. While the script is being evaluated, it may not have added the plugin to the classpath yet. This is one of many reasons why the `plugins` block was created, as it's specifically evaluated prior to the rest of the scripts evaluation during a `buildscript` phase. That said however, this special treatment is only done if this block is at the top of the script, and not when it's within a `subprojects` or `allprojects` block, as those blocks are technically arbitrary and are evaluated later to ensure the `buildscript` is idempotent. In your case, you are just moving up the race by placing it in `allprojects` block, and are getting lucky.  
> 이 문제는 Kotlin에만 국한되지 않으며 경쟁 조건으로 인한 것입니다. 스크립트가 평가되는 동안 아직 클래스 경로에 플러그인을 추가하지 않았을 수 있습니다. 이것은 `plugins` 블록이 생성된 많은 이유 중 하나입니다. `buildscript` 단계에서 나머지 스크립트 평가 전에 특별히 평가되기 때문입니다. 그러나 이 특별한 처리는 이 블록이 스크립트의 맨 위에 있는 경우에만 수행되며 `subprojects` 또는 `allprojects` 블록 내에 있는 경우에는 수행되지 않습니다. 이러한 블록은 기술적으로 임의적이며 나중에 ` buildscript`는 멱등원입니다. 귀하의 경우에는 `allprojects` 블록에 배치하여 경주를 진행하고 있으며 운이 좋아지고 있습니다.
> 
> When dealing with multi-project builds, this is problematic, however if possible, the best is to declare the plugin in the `plugins` block with the apply false constrained syntax to add it to your build's classpath in the `buildscript` phase. You will then be able to apply the plugin later via the plugin's id during script evaluation (version isn't necessary, as it's used for fetching the dependency only).  
> 다중 프로젝트 빌드를 처리할 때 이것은 문제가 됩니다. 그러나 가능하면 'buildscript' 단계에서 빌드의 클래스 경로에 추가하기 위해 apply false 제약 구문을 사용하여 'plugins' 블록에서 플러그인을 선언하는 것이 가장 좋습니다. 그런 다음 나중에 스크립트 평가 중에 플러그인의 ID를 통해 플러그인을 적용할 수 있습니다(버전은 종속성을 가져오는 데만 사용되므로 버전은 필요하지 않음).

## [APIs](https://gradle.github.io/kotlin-dsl-docs/api/)

### [PluginDependenciesSpec](https://gradle.github.io/kotlin-dsl-docs/api/org.gradle.plugin.use/-plugin-dependencies-spec/index.html)

스크립트에서 사용할 플러그인을 선언하기 위한 DSL. 빌드 스크립트에서 `plugins {}` 블록이 이 타입에 해당한다. 따라서 이 API를 `plugins {}` 블록 바디에서 플러그인을 선언하는 데에 사용할 수 있다.

#### `apply()` 메서드와의 관계

`plugins {}` 블록은 `Project` 오브젝트에 직접적으로 플러그인을 저용하는 데 사용되는 `org.gradle.api.plugins.PluginAware#apply(java.util.Map)` 메서드와 비슷한 용도로 사용된다.

중요한 차이점은 `plugins {}` 블록을 통해 적용된 플러그인들은 개념적으로 스크립트에 적용되고, 더 나아가 스크립트 대상에 적용된다는 것.
`plugin {}` 블록은 성장하고 있는 신규 기능이다. 