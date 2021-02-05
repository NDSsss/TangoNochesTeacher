package ru.nds.teacher.login.domain.interactor

import io.reactivex.Single
import ru.nds.teacher.login.domain.entity.LoginEntity

interface ILoginInteractor {
    fun login(email: String, password: String): Single<LoginEntity>
}