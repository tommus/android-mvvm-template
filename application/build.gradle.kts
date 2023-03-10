@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.ProguardFiles.ProguardFile.OPTIMIZE
import windly.template.ci.Application.Debug.packageSuffix
import windly.template.ci.Application.Debug.versionSuffix
import windly.template.ci.Application.debugAppName
import windly.template.ci.Application.packageName
import windly.template.ci.Application.releaseAppName
import windly.template.ci.Build
import windly.template.ci.Build.Android
import windly.template.ci.Build.Variant.DEBUG
import windly.template.ci.Build.Variant.RELEASE
import windly.template.ci.Keystore
import windly.template.ci.Proguard.rules
import windly.template.task.PrintVersionTask

plugins {
  id("com.android.application")
  id("com.google.devtools.ksp")
  id("dagger.hilt.android.plugin")
  id("co.windly.versioning")
  kotlin("android")
  kotlin("kapt")
  id("androidx.navigation.safeargs.kotlin")
}

android {

  namespace = packageName

  buildFeatures {
    dataBinding = true
  }

  signingConfigs {

    getByName(DEBUG) {
      with(Keystore.Debug) {
        keyAlias = alias
        keyPassword = key
        storePassword = key
        storeFile = rootProject.file(path)
      }
    }

    create(RELEASE) {


      with(Keystore.Release) {
        keyAlias = alias
        keyPassword = key
        storePassword = key
        storeFile = rootProject.file(path)
      }
    }
  }

  buildTypes {

    debug {

      applicationIdSuffix = packageSuffix

      isMinifyEnabled = false
      resValue("string", "app_name", debugAppName())
      signingConfig = signingConfigs.getByName(DEBUG)
      versionNameSuffix = " $versionSuffix"
    }

    release {

      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile(OPTIMIZE.fileName), rules())
      signingConfig = signingConfigs.getByName(RELEASE)
      resValue("string", "app_name", releaseAppName())
    }
  }

  compileOptions {
    sourceCompatibility = Build.Version.java
    targetCompatibility = Build.Version.java
  }
  compileSdk = Android.compileSdk

  defaultConfig {
    applicationId = packageName
    minSdk = Android.minSdk
    targetSdk = Android.targetSdk
    versionCode = versioning.versionCode()
    versionName = versioning.versionName()
  }
}

dependencies {

  implementation(project(":base:android"))
  implementation(project(":base:language"))
  implementation(project(":base:mvvm"))
  implementation(project(":configuration"))
  implementation(project(":resources"))

  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.navigation.fragment.ktx)
  implementation(libs.androidx.navigation.ui.ktx)

  implementation(libs.fastadapter.binding)

  implementation(libs.google.material)

  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  implementation(libs.kotlinx.coroutines.rx3)

  implementation(libs.rx.java)
}

tasks.register<PrintVersionTask>(
  name = PrintVersionTask.NAME,
  versioning.versionCode(),
  versioning.versionName()
)
