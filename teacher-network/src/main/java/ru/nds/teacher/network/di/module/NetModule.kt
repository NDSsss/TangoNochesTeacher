package ru.nds.teacher.network.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nds.teacher.network.BuildConfig
import ru.nds.teacher.network.interceptors.IApiTokenInterceptor
import ru.nds.teacher.network.utils.DateDeserializer
import ru.nds.teacher.network.utils.DateSerializer
import java.util.*

val netModule = module {
    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .registerTypeAdapter(Date::class.java, DateSerializer())
            .create()
    }

    factory<OkHttpClient> {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(get<IApiTokenInterceptor>())
        clientBuilder.build()
    }

    factory<Retrofit>{
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }
}