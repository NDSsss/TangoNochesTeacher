package ru.nds.teacher.login.domain.interactor

import io.reactivex.Single
import ru.nds.teacher.login.domain.entity.LoginEntity
import ru.nds.teacher.login.domain.repository.ILoginRepository

class ILoginInteractorImpl(
    private val loginRepository: ILoginRepository
) : ILoginInteractor {
    override fun login(email: String, password: String): Single<LoginEntity> =
        loginRepository.login(email, password)
}