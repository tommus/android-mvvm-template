@file:Suppress("UnstableApiUsage")

import windly.template.ci.Application.packageName
import windly.template.ci.Build
import windly.template.ci.Build.Android
import windly.template.ci.Proguard.CONSUMER

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {

  namespace = "$packageName.resources"

  buildFeatures {
    buildConfig = false
  }

  compileOptions {
    sourceCompatibility = Build.Version.java
    targetCompatibility = Build.Version.java
  }
  compileSdk = Android.compileSdk

  defaultConfig {
    consumerProguardFiles(CONSUMER)
    minSdk = Android.minSdk
  }
}

dependencies {
  implementation(libs.google.material)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}
