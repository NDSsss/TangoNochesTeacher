package ru.nds.teacher.login.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.nds.teacher.login.data.api.LoginApiService
import ru.nds.teacher.login.data.dataSource.login.ILoginDataSource
import ru.nds.teacher.login.data.dataSource.login.LoginDataSource
import ru.nds.teacher.login.data.repository.login.LoginRepository
import ru.nds.teacher.login.domain.repository.ILoginRepository

val teacherLoginDataModule = module {
    factory<LoginApiService> {
        get<Retrofit>().create(LoginApiService::class.java)
    }
    factory<ILoginDataSource> {
        LoginDataSource(schedulers = get(), loginApiService = get())
    }
    factory<ILoginRepository> {
        LoginRepository(loginDataSource = get(), prefsStorage = get())
    }
}