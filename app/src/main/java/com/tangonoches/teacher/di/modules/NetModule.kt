package com.tangonoches.teacher.di.modules

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tangonoches.teacher.domain.datasources.web.interceptors.IApiTokenInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .create()

    @Provides
    @Singleton
    fun provideOkkHttpClient(apiTokenInterceptor: IApiTokenInterceptor): OkHttpClient {
        Log.d("APP_TAG", "provideOkkHttpClient")
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(apiTokenInterceptor)
        return clientBuilder.build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(okkClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://ndssss.beget.tech/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okkClient)
            .build()

}