package windly.template.ci

import org.gradle.api.JavaVersion

object Build {

  object Android {
    const val compileSdk = 33
    const val minSdk = 27
    const val targetSdk = 33
  }

  object Version {
    val java = JavaVersion.VERSION_11
    const val kotlin = "11"
  }

  object Variant {
    const val DEBUG = "debug"
    const val RELEASE = "release"
  }
}
