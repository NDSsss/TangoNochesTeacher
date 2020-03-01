import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
}

android {
    val ext = rootProject.extra
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")

    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = ext["javaVer"] as JavaVersion
        targetCompatibility = ext["javaVer"] as JavaVersion
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    val ext = rootProject.extra
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation(ext["kotlin-stdlib"] as String)

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.navigation:navigation-fragment:2.2.1")
    implementation("androidx.navigation:navigation-ui:2.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.1")

    (ext["baseAndroidDependencies"] as List<String>)
            .plus(ext["baseRxDependencies"] as List<String>)
            .plus(ext["baseNetworkDependencies"] as List<String>)
            .forEach {
                implementation(it)
            }

    implementation("com.jakewharton.rxrelay2:rxrelay:2.1.1")
    implementation("me.dm7.barcodescanner:zxing:1.9.13")
    implementation("me.dm7.barcodescanner:zbar:1.9.13")
    implementation("org.conscrypt:conscrypt-android:2.2.1")
    implementation("androidx.multidex:multidex:2.0.0")

    implementation(ext["dagger"] as String)
    kapt(ext["daggerCompiler"] as String)
    kaptAndroidTest(ext["daggerCompiler"] as String)
    compileOnly(ext["daggerAnnotations"] as String)
    kaptTest(ext["daggerCompiler"] as String)
    testAnnotationProcessor(ext["daggerAnnotations"] as String)

    testImplementation(ext["jUnit"] as String)
    androidTestImplementation(ext["testRunner"] as String)
    androidTestImplementation(ext["espressoCore"] as String)
}
