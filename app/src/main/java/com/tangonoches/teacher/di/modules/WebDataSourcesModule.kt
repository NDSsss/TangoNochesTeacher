package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.datasources.web.login.ILoginDataSource
import com.tangonoches.teacher.domain.datasources.web.login.LoginDataSource
import com.tangonoches.teacher.domain.services.LoginService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WebDataSourcesModule {
    @Provides
    @Singleton
    fun provideloginDataSource(loginService: LoginService): ILoginDataSource =
        LoginDataSource(loginService)
}