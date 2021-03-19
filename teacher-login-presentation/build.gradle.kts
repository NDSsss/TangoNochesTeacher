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
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation(project(Modules.common))
    implementation(project(Modules.arch))
    implementation(project(Modules.teacherNetwork))
    implementation(project(Modules.Teacher.Login.domain))
    implementation(project(Modules.Teacher.Login.data))
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.Google.Dagger.dagger)
    kapt(Dependencies.Google.Dagger.daggerCompiler)
    implementation(Dependencies.Reactivex.RxJava2.rxJava)

    implementation(Dependencies.Jakewharton.RxRelay2.rxRelay)
    implementation(Dependencies.Jakewharton.RxBinding.rxBinding)

    implementation(Dependencies.SquareUp.Retrofit.retrofit)
    implementation(Dependencies.SquareUp.Retrofit.converterGson)
    implementation(Dependencies.SquareUp.Retrofit.adapterRxJava2)
    implementation(Dependencies.SquareUp.OkkHttp3.loggingInterceptor)
    implementation(Dependencies.SquareUp.OkkHttp3.urlConnection)

//    kaptAndroidTest(ext["daggerCompiler"] as String)
//    compileOnly(ext["daggerAnnotations"] as String)
//    kaptTest(ext["daggerCompiler"] as String)
//    testAnnotationProcessor(ext["daggerAnnotations"] as String)
}