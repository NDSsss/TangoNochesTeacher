package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
import com.tangonoches.teacher.domain.datasources.web.interceptors.ApiTokenInterceptor
import com.tangonoches.teacher.domain.datasources.web.interceptors.IApiTokenInterceptor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InterceptorsModule {
    @Provides
    @Singleton
    fun provideApiTokenOnterceptor(prefsStorage: IPrefsStorage): IApiTokenInterceptor =
        ApiTokenInterceptor(prefsStorage)
}