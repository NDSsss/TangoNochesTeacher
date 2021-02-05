plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.tangonoches.plugin.lib")
    id("kotlin-kapt")
}

android {


    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"http://tangonoches.famedev-stage.ru/api/\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"http://tangonoches.famedev.ru/api/\"")
        }
    }
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

    implementation(project(Modules.common))


    implementation(Dependencies.SquareUp.Retrofit.retrofit)
    implementation(Dependencies.SquareUp.Retrofit.converterGson)
    implementation(Dependencies.SquareUp.Retrofit.adapterRxJava2)
    implementation(Dependencies.SquareUp.OkkHttp3.loggingInterceptor)
    implementation(Dependencies.SquareUp.OkkHttp3.urlConnection)
}