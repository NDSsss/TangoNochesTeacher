package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.services.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    @Singleton
    fun providesLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun providesLessonsService(retrofit: Retrofit): LessonsService =
        retrofit.create(LessonsService::class.java)

    @Provides
    @Singleton
    fun providesGroupsService(retrofit: Retrofit): GroupsService =
        retrofit.create(GroupsService::class.java)

    @Provides
    @Singleton
    fun providesTicketEventTypesService(retrofit: Retrofit): TicketEventTypesService =
        retrofit.create(TicketEventTypesService::class.java)

    @Provides
    @Singleton
    fun providesTicketCountTypesService(retrofit: Retrofit): TicketCountTypesService =
        retrofit.create(TicketCountTypesService::class.java)

    @Provides
    @Singleton
    fun providesStudentsService(retrofit: Retrofit): StudentsService =
        retrofit.create(StudentsService::class.java)

    @Provides
    @Singleton
    fun providesTeachersService(retrofit: Retrofit): TeachersService =
        retrofit.create(TeachersService::class.java)


}