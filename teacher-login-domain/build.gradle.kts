plugins {
    `java-library`
    kotlin
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Dependencies.kotlinVer}")
    implementation(Dependencies.Reactivex.RxJava2.rxJava)
}