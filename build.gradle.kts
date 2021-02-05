// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.4.21")
    rootProject.apply(from = "gradle/dependencies.gradle.kts")

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
        classpath("com.google.gms:google-services:4.3.3")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
