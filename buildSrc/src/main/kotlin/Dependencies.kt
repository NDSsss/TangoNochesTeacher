object Dependencies {
    const val BASE_TANGONOCHES_ID = "com.tangonoches"

    const val kotlinVer = "1.4.21"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val ANDROID_TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    object AndroidX {
        object Lifecycle {
            private const val lifecycleVersion = "2.2.0"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            private const val lifecycleExtensionsVer = "2.1.0"
            const val lifecycleExtensions =
                "androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionsVer"
        }

        private const val constraintLayoutVersion = "1.1.3"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    }

    object Google {
        object Dagger {
            private const val daggerVer = "2.25.2"
            const val dagger = "com.google.dagger:dagger:$daggerVer"
            const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVer"
        }
    }

    object Koin {
        object Android {
            private const val koinVersion ="2.0.1"
            const val android = "org.koin:koin-android:$koinVersion"
            const val viewModel = "org.koin:koin-android-viewmodel:$koinVersion"
        }
    }

    object Reactivex {
        object RxJava2 {
            private const val rxJavaVersion = "2.2.15"
            const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
            private const val rxAndroidVersion = "2.2.15"
            const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
            private const val rxKotlinVersion = "2.2.15"
            const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion"
        }
    }

    object Jakewharton {
        object RxRelay2 {
            private const val rxRelayVersion = "2.1.1"
            const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:$rxRelayVersion"
        }

        object RxBinding {
            private const val rxBindingVersion = "2.2.0"
            const val rxBinding = "com.jakewharton.rxbinding2:rxbinding-kotlin:$rxBindingVersion"
        }
    }

    object SquareUp {
        object Retrofit {
            private const val retrofitVersion = "2.6.2"
            const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
            const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
            const val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
        }

        object OkkHttp3 {
            private const val okkHttp3Ver = "4.2.1"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okkHttp3Ver"
            const val urlConnection = "com.squareup.okhttp3:okhttp-urlconnection:$okkHttp3Ver"
        }
    }
}