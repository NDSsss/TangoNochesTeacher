package ru.nds.teacher.network.di.module

import dagger.Module
import dagger.Provides
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.teacher.network.di.TeacherNetworkScope
import ru.nds.teacher.network.interceptors.ApiTokenInterceptor
import ru.nds.teacher.network.interceptors.IApiTokenInterceptor

@Module
class InterceptorsModule {
    @Provides
    @TeacherNetworkScope
    fun provideApiTokenInterceptor(prefsStorage: IPrefsStorage): IApiTokenInterceptor =
        ApiTokenInterceptor(prefsStorage)
}