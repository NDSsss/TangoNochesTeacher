package ru.nds.teacher.network.interceptors

import ru.nds.core.domain.prefs.IPrefsStorage
import okhttp3.Interceptor
import okhttp3.Response

class ApiTokenInterceptor(val prefsStorage: IPrefsStorage) : IApiTokenInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Authorization", prefsStorage.teacherToken)
                    .build()
            )
        }
}