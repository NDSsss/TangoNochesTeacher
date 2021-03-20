package ru.nds.teacher.network.di.module

import org.koin.dsl.module
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.teacher.network.interceptors.ApiTokenInterceptor
import ru.nds.teacher.network.interceptors.IApiTokenInterceptor

val teacherInterceptorModule = module {
    factory<IApiTokenInterceptor> {
        ApiTokenInterceptor(
            prefsStorage = get()
        )
    }
}