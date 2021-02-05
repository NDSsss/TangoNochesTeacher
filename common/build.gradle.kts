plugins {
    id("com.android.library")
    id("kotlin-android")
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
    implementation("androidx.core:core-ktx:1.3.2")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation(Dependencies.Google.Dagger.dagger)
    kapt(Dependencies.Google.Dagger.daggerCompiler)

    implementation(Dependencies.Reactivex.RxJava2.rxJava)
    implementation(Dependencies.Jakewharton.RxBinding.rxBinding)
}