package com.tangonoches.teacher.domain.repositories.login

import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
import com.tangonoches.teacher.domain.datasources.web.login.ILoginDataSource
import com.tangonoches.teacher.domain.repositories.login.ILoginRepository
import io.reactivex.Single

class LoginRepository(
    private val loginDataSource: ILoginDataSource,
    private val prefsStorage: IPrefsStorage
) : ILoginRepository {
    override fun login(email: String, password: String): Single<String> =
        loginDataSource.login(email, password)
            .doOnSuccess {
                prefsStorage.teacherToken = it
                prefsStorage.teacherEmail = email
            }
}