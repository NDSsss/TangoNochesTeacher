package ru.nds.teacher.network.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nds.teacher.network.BuildConfig
import ru.nds.teacher.network.di.TeacherNetworkScope
import ru.nds.teacher.network.interceptors.IApiTokenInterceptor
import ru.nds.teacher.network.utils.DateDeserializer
import ru.nds.teacher.network.utils.DateSerializer
import java.util.*

@Module
class NetModule {
    @Provides
    @TeacherNetworkScope
    internal fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .registerTypeAdapter(Date::class.java, DateSerializer())
        .create()

    @Provides
    @TeacherNetworkScope
    fun provideOkkHttpClient(apiTokenInterceptor: IApiTokenInterceptor): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(apiTokenInterceptor)
        return clientBuilder.build()
    }


    @Provides
    @TeacherNetworkScope
    fun providesRetrofit(okkClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okkClient)
            .build()

}