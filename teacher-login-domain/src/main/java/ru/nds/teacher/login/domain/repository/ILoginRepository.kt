package ru.nds.teacher.login.domain.repository

import io.reactivex.Single
import ru.nds.teacher.login.domain.entity.LoginEntity

interface ILoginRepository {
    fun login(email: String, password: String): Single<LoginEntity>
}