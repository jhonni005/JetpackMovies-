import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
        id("kotlin-kapt")

}

android {
    namespace = "com.zonadev.myapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.zonadev.myapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String","apiKey", properties.getProperty("apiKey"))


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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    implementation(libs.kotlinx.serialization.json)
    //Navigation
    implementation(libs.androidx.navigation.compose.v275)

    implementation(libs.coil3.coil.compose)

    // ViewModel para Jetpack Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

// ViewModel con soporte para corrutinas (viewModelScope)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

// Runtime con extensiones KTX para lifecycle (lifecycleScope, etc.)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Coroutines núcleo
    implementation (libs.jetbrains.kotlinx.coroutines.core)

    // Coroutines para Android
    implementation (libs.kotlinx.coroutines.android)

    //Coil
    implementation(libs.coil.compose)

    // Convertidor Gson para Retrofit
    implementation (libs.retrofit2.converter.gson)

    //Cliente Retrofit http
    implementation (libs.retrofit2.retrofit)

    //navigation compose
    implementation(libs.androidx.navigation.compose)

    // 📌 Paging 3 (Paginación de datos)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose) // P


    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}