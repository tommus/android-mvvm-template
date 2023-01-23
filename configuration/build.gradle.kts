@file:Suppress("UnstableApiUsage")

import Build_gradle.Key.URL
import windly.template.ci.Application.packageName
import windly.template.ci.Build
import windly.template.ci.Build.Android
import windly.template.ci.Proguard.CONSUMER

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

object Key {
  const val URL = "URL"
}

android {

  namespace = "$packageName.configuration"

  compileOptions {
    sourceCompatibility = Build.Version.java
    targetCompatibility = Build.Version.java
  }
  compileSdk = Android.compileSdk

  defaultConfig {
    consumerProguardFiles(CONSUMER)
    minSdk = Android.minSdk
  }

  buildTypes {
    debug {
      isMinifyEnabled = false

      // TODO: Add injectable configuration.
      buildConfigField("String", URL, findProperty("ApiUrlDebug").toString())
    }
    release {
      isMinifyEnabled = false

      // TODO: Add injectable configuration.
      buildConfigField("String", URL, findProperty("ApiUrlRelease").toString())
    }
  }
}

dependencies {
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}
