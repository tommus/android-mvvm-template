@file:Suppress("UnstableApiUsage")

import windly.template.ci.Application.packageName
import windly.template.ci.Build
import windly.template.ci.Build.Android
import windly.template.ci.Build.Version
import windly.template.ci.Proguard.CONSUMER

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {

  namespace = "$packageName.network"

  buildFeatures {
    buildConfig = false
  }

  compileOptions {
    sourceCompatibility = Version.java
    targetCompatibility = Version.java
  }
  compileSdk = Android.compileSdk

  defaultConfig {
    consumerProguardFiles(CONSUMER)
    minSdk = Android.minSdk
  }
}

dependencies {

  implementation(project(":base:language"))
  implementation(project(":configuration"))

  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  implementation(libs.network.moshi)
  kapt(libs.network.moshi.codegen)

  implementation(libs.network.okhttp)
  implementation(libs.network.okhttp.logging)

  api(libs.bundles.network.retrofit)
  implementation(libs.network.retrofit.rxjava3)
}
