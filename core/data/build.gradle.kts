import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.apollographql.apollo3)
}

val envPropertiesFile: File? = rootProject.file("env.properties")
val envProperties = Properties()
envProperties.load(envPropertiesFile?.let { FileInputStream(it) })


android {
    namespace = "com.amalitech.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "GRAPHQL_URL", envProperties["GRAPHQL_URL"]?.toString() ?: "")
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
    implementation(libs.appolo.runtime)
    implementation(libs.androidx.datastore.preferences)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
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
