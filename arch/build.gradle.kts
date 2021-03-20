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
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation(project(Modules.common))
    implementation(Dependencies.Koin.Android.viewModel)

    implementation(Dependencies.Reactivex.RxJava2.rxJava)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleExtensions)
    implementation(Dependencies.Koin.Android.android)

    implementation(Dependencies.SquareUp.Retrofit.retrofit)
    implementation(Dependencies.SquareUp.Retrofit.converterGson)
    implementation(Dependencies.SquareUp.Retrofit.adapterRxJava2)
    implementation(Dependencies.SquareUp.OkkHttp3.loggingInterceptor)
    implementation(Dependencies.SquareUp.OkkHttp3.urlConnection)

    implementation(Dependencies.Jakewharton.RxRelay2.rxRelay)
}