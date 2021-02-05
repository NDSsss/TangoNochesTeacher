package ru.nds.teacher.login.data.repository.login

import io.reactivex.Single
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.teacher.login.data.dataSource.login.ILoginDataSource
import ru.nds.teacher.login.domain.entity.LoginEntity
import ru.nds.teacher.login.domain.repository.ILoginRepository

class LoginRepository(
    private val loginDataSource: ILoginDataSource,
    private val prefsStorage: IPrefsStorage
) : ILoginRepository {
    override fun login(email: String, password: String): Single<LoginEntity> =
        loginDataSource.login(email, password)
            .map { it.toEntity() }
            .doOnSuccess {
                prefsStorage.teacherToken = it.apiToken
                prefsStorage.teacherEmail = email
            }
}