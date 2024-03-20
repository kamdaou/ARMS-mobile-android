import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    kotlin("kapt")
}

val envPropertiesFile: File? = rootProject.file("env.properties")
val envProperties = Properties()
envProperties.load(envPropertiesFile?.let { FileInputStream(it) })

android {

    namespace = "com.amalitech.arms_mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amalitech.arms_mobile"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "GRAPHQL_URL", envProperties["GRAPHQL_URL"]?.toString() ?: "")
    }

    signingConfigs {
        create("release") {
            storeFile = file(envProperties["STORE_FILE"]?.toString() ?: "")
            storePassword = envProperties["STORE_PASSWORD"]?.toString() ?: ""
            keyAlias = envProperties["KEY_ALIAS"]?.toString() ?: ""
            keyPassword = envProperties["KEY_PASSWORD"]?.toString() ?: ""
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["release"]
        }

        debug {
            signingConfig = signingConfigs["release"]

            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources.excludes.add("META-INF/*")
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":ui:home"))
    implementation(project(":data:home"))
    implementation(project(":domain:home"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    testImplementation(libs.junit)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.espresso.core)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    kapt(libs.hilt.android.compiler)

    implementation(libs.bundles.hilt)
    implementation(libs.kotlinx.datetime)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.volley)
    implementation(libs.legacy.support.v4)
    implementation(libs.opentelemetry.api)
    implementation(libs.opentelemetry.context)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.transport.runtime)
    implementation(libs.msal)
    {
        exclude(group = "io.opentelemetry")
        exclude(group = "com.microsoft.device.display")
    }
    implementation(libs.microsoft.graph)
    implementation(libs.azure.identity)

    implementation(libs.androidx.lifecycle.runtime.compose)
}

kapt {
    correctErrorTypes = true
}
