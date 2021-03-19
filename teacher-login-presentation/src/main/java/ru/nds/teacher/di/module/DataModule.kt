package ru.nds.teacher.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nds.common.rx.ISchedulers
import ru.nds.core.domain.prefs.IPrefsStorage
import ru.nds.teacher.di.TeacherLoginScope
import ru.nds.teacher.login.data.api.LoginApiService
import ru.nds.teacher.login.data.dataSource.login.ILoginDataSource
import ru.nds.teacher.login.data.dataSource.login.LoginDataSource
import ru.nds.teacher.login.data.repository.login.LoginRepository
import ru.nds.teacher.login.domain.repository.ILoginRepository

@Module
internal class DataModule {

    @Provides
    @TeacherLoginScope
    fun provideLoginApi(retrofit: Retrofit): LoginApiService =
        retrofit.create(LoginApiService::class.java)

    @Provides
    @TeacherLoginScope
    fun provideLoginDataSource(schedulers: ISchedulers, api: LoginApiService): ILoginDataSource =
        LoginDataSource(schedulers = schedulers, loginApiService = api)


    @Provides
    @TeacherLoginScope
    fun provideLoginRepository(
        dataSource: ILoginDataSource,
        prefs: IPrefsStorage
    ): ILoginRepository =
        LoginRepository(loginDataSource = dataSource, prefsStorage = prefs)
}