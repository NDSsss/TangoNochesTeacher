package com.tangonoches.teacher.domain.datasources.web.interceptors

import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
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