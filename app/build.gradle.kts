plugins {
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.samsung.health.mysteps"
    compileSdk = 36

    defaultConfig {
        minSdk = 31
        versionCode = 1

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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    configurations.all {
        resolutionStrategy {
            // This forces Gradle to NEVER include the old Material 1 library
            force("androidx.compose.material3:material3:${libs.versions.material3.get()}")
            exclude(group = "androidx.compose.material", module = "material")
            exclude(group = "androidx.compose.material", module = "material-icons-core")
            exclude(group = "androidx.compose.material", module = "material-icons-extended")
            force ("com.google.android.gms:play-services-basement:18.0.1")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation("com.google.android.gms:play-services-basement:18.0.1")
    implementation(libs.core.ktx)

    //compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    implementation(libs.material3)
    implementation(libs.activity.compose)
    //tests
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.slf4j)
    implementation(libs.junit.ktx)

    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.gson)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
