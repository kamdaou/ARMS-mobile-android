import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3") version "4.0.0-beta.4"
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

val envPropertiesFile: File? = rootProject.file("env.properties")
val envProperties = Properties()
envProperties.load(envPropertiesFile?.let { FileInputStream(it) })

android {

    namespace = "com.amalitech.arms_mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amalitech.arms_mobile.test"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources.excludes.add("META-INF/*")
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.core:core-ktx:1.12.0")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    kapt("com.google.dagger:hilt-android-compiler:2.50")

    implementation("com.google.dagger:hilt-android:2.50")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.apollographql.apollo3:apollo-runtime")
    implementation("io.opentelemetry:opentelemetry-api:1.18.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("io.opentelemetry:opentelemetry-context:1.18.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.android.datatransport:transport-runtime:3.2.0")
    implementation("com.microsoft.identity.client:msal:4.10.0")
    {
        exclude(group = "io.opentelemetry")
        exclude(group = "com.microsoft.device.display")
    }
    implementation("com.microsoft.graph:microsoft-graph:5.80.0")
    implementation("com.azure:azure-identity:1.10.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
}

kapt {
    correctErrorTypes = true
}

apollo {
    service("service") {
        packageName.set("com.amalitech")
        introspection {
            endpointUrl.set(envProperties["graphqlUrl"]?.toString() ?: "")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }
        generateKotlinModels.set(true)
    }
}