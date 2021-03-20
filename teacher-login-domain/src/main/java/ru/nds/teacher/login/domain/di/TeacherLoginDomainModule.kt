package ru.nds.teacher.login.domain.di

import org.koin.dsl.module
import ru.nds.teacher.login.domain.interactor.ILoginInteractor
import ru.nds.teacher.login.domain.interactor.LoginInteractor


val teacherLoginDomainModule = module {
    factory<ILoginInteractor> {
        LoginInteractor(
            loginRepository = get()
        )
    }
}