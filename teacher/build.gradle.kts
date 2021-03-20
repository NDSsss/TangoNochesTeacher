import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.tangonoches.plugin.teacher")
}

android {
    logger.log(org.gradle.api.logging.LogLevel.WARN,"teacher android ${this::class.simpleName}")
    signingConfigs {
        create("release") {
            storeFile = file("${project.projectDir.absolutePath}/../tango_noches_teacher.jks")
            storePassword = "Parolmoi1997"
            keyAlias = "tango_noches_teacher_alias"
            keyPassword = "Parolmoi1997"
        }
    }

    buildTypes {
        val appName = "TangoNoches Teacher"
        val apkBaseName = "TangoNoches_Teacher"
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            buildConfigField("String", "BASE_URL", "\"http://tangonoches.famedev-stage.ru/api/\"")
            resValue("string", "app_name_build", "Debug $appName Stage")

            setProperty("archivesBaseName", "Debug ${apkBaseName}_v${(properties["versionName"])}")
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("String", "BASE_URL", "\"http://tangonoches.famedev.ru/api/\"")
            resValue("string", "app_name_build", appName)
        }
    }

}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    val ext = rootProject.extra
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation(project(Modules.common))
    implementation(project(Modules.teacherNetwork))
    implementation(project(Modules.arch))
    implementation(project(Modules.Teacher.Login.presentation))

    implementation(ext["kotlin-stdlib"] as String)

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.navigation:navigation-fragment:2.3.0")
    implementation("androidx.navigation:navigation-ui:2.3.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0")
    implementation("com.google.android:flexbox:1.1.0")
//    implementation("com.github.jakebonk:ChipView:1.0.1")

    //FIXME: class file for javax.annotation.Nullable not found
    implementation("com.google.code.findbugs:jsr305:3.0.2")

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
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.firebase:firebase-analytics:18.0.0")
    implementation("com.google.firebase:firebase-messaging:21.0.1")
    implementation(Dependencies.Koin.Android.android)

    implementation(Dependencies.Koin.Android.android)
    implementation(Dependencies.Koin.Android.viewModel)

    testImplementation(ext["jUnit"] as String)
    androidTestImplementation(ext["testRunner"] as String)
    androidTestImplementation(ext["espressoCore"] as String)
}
