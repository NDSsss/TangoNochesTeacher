package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
import com.tangonoches.teacher.domain.datasources.web.login.ILoginDataSource
import com.tangonoches.teacher.domain.repositories.ILoginRepository
import com.tangonoches.teacher.domain.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginDataSource: ILoginDataSource, prefsStorage: IPrefsStorage)
            : ILoginRepository = LoginRepository(loginDataSource, prefsStorage)
}