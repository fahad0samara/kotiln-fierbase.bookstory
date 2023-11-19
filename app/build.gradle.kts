@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.kotlinAndroid)

  alias(libs.plugins.googleServices)
  alias(libs.plugins.hiltAndroid)
  alias(libs.plugins.kotlinKsp)
}

android {
    namespace = "com.fahad.list_food"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fahad.list_food"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)






    //Room
    val room_version = "2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:$room_version")

  //Dagger Hilt
  implementation(libs.hilt.android)
  ksp(libs.hiltAndroidCompiler)
  //Room
  implementation(libs.androidx.room.runtime.v260)
  ksp(libs.androidx.room.compiler.v260)
  implementation (libs.androidx.room.ktx.v260)

  //lifecycle
  implementation(libs.androidx.lifecycle.viewmodel.compose.v262)
  //navigation
  implementation(libs.navigation.compose)
  implementation(libs.androidx.hilt.navigation.compose.v100)






  //firebase
  implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
  //noinspection UseTomlInstead
  implementation("com.google.firebase:firebase-analytics-ktx")
  implementation("com.google.firebase:firebase-auth-ktx")
  implementation(libs.firebase.storage.ktx)




  //phothoView
  implementation (libs.coil.compose)




}
ksp {
  // All KSP Gradle plugin options
  arg("com.google.devtools.ksp.incremental.apt", "true")


}