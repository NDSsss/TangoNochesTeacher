package ru.nds.teacher.network.di

import android.content.Context
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.nds.common.di.CommonComponent
import ru.nds.common.navigation.CoordinatorProvider
import ru.nds.common.rx.ISchedulers
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.teacher.network.di.module.InterceptorsModule
import ru.nds.teacher.network.di.module.NetModule

@Component(
    dependencies = [
        CommonComponent::class
    ],
    modules = [
        InterceptorsModule::class,
        NetModule::class
    ]
)
@TeacherNetworkScope
interface TeacherNetworkComponent {
    fun provideGson(): Gson
    fun provideOkHttpClient(): OkHttpClient

    fun provideContext(): Context
    fun provideSchedulers(): ISchedulers
    fun provideCoordinatorProvider(): CoordinatorProvider
    fun provideIPrefsStorage(): IPrefsStorage
    fun provideRetrofit(): Retrofit
}