package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.di.TeacherScope
import com.tangonoches.teacher.domain.services.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServicesModule {

//    @Provides
//    @TeacherScope
//    fun providesLoginService(retrofit: Retrofit): LoginService =
//        retrofit.create(LoginService::class.java)

    @Provides
    @TeacherScope
    fun providesLessonsService(retrofit: Retrofit): LessonsService =
        retrofit.create(LessonsService::class.java)

    @Provides
    @TeacherScope
    fun providesGroupsService(retrofit: Retrofit): GroupsService =
        retrofit.create(GroupsService::class.java)

    @Provides
    @TeacherScope
    fun providesTicketEventTypesService(retrofit: Retrofit): TicketEventTypesService =
        retrofit.create(TicketEventTypesService::class.java)

    @Provides
    @TeacherScope
    fun providesTicketCountTypesService(retrofit: Retrofit): TicketCountTypesService =
        retrofit.create(TicketCountTypesService::class.java)

    @Provides
    @TeacherScope
    fun providesStudentsService(retrofit: Retrofit): StudentsService =
        retrofit.create(StudentsService::class.java)

    @Provides
    @TeacherScope
    fun providesTeachersService(retrofit: Retrofit): TeachersService =
        retrofit.create(TeachersService::class.java)

    @Provides
    @TeacherScope
    fun providesTicketsService(retrofit: Retrofit): TicketsService =
        retrofit.create(TicketsService::class.java)


}