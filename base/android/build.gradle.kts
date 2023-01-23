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

  namespace = "$packageName.android"

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

  implementation(project(":base:language"))
  implementation(project(":configuration"))

  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  api(libs.timber)
}
