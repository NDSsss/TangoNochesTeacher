plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android.extensions")
    id("com.tangonoches.plugin.lib")
    id("kotlin-kapt")
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Dependencies.kotlinVer}")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation(project(Modules.common))
    implementation(project(Modules.arch))
    implementation(project(Modules.Teacher.Login.domain))

    implementation(Dependencies.Koin.Android.android)

    implementation(Dependencies.SquareUp.Retrofit.retrofit)
    implementation(Dependencies.SquareUp.Retrofit.adapterRxJava2)
    implementation(Dependencies.Reactivex.RxJava2.rxJava)
    implementation(Dependencies.Jakewharton.RxBinding.rxBinding)
}