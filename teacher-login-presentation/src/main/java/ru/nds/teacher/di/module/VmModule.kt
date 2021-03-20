package ru.nds.teacher.di.module

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nds.teacher.login.data.di.teacherLoginDataModule
import ru.nds.teacher.login.domain.di.teacherLoginDomainModule
import ru.nds.teacher.presentation.login.LoginVm

val teacherLoginPresentationVmModule = module {
    viewModel {
        LoginVm(
            loginRepository = get()
        )
    }
}.plus(
    teacherLoginDataModule
).plus(teacherLoginDomainModule)