package ru.nds.teacher.di.module

import dagger.Module
import dagger.Provides
import ru.nds.teacher.di.TeacherLoginScope
import ru.nds.teacher.login.domain.interactor.ILoginInteractor
import ru.nds.teacher.login.domain.interactor.LoginInteractor
import ru.nds.teacher.login.domain.repository.ILoginRepository

@Module
internal class DomainModule {

    @Provides
    @TeacherLoginScope
    fun provideLoginInteractor(repository: ILoginRepository): ILoginInteractor = LoginInteractor(
        loginRepository = repository
    )
}