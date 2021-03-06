package com.tangonoches.teacher.di.modules

import com.google.gson.Gson
import com.tangonoches.teacher.domain.datasources.web.groups.GroupsDataSource
import com.tangonoches.teacher.domain.datasources.web.groups.IGroupsDataSource
import com.tangonoches.teacher.domain.datasources.web.lessons.ILessonsDataSource
import com.tangonoches.teacher.domain.datasources.web.lessons.LessonsDataSource
import com.tangonoches.teacher.domain.datasources.web.login.ILoginDataSource
import com.tangonoches.teacher.domain.datasources.web.login.LoginDataSource
import com.tangonoches.teacher.domain.datasources.web.students.IStudentsDataSource
import com.tangonoches.teacher.domain.datasources.web.students.StudentsDataSource
import com.tangonoches.teacher.domain.datasources.web.teachers.ITeachersDataSource
import com.tangonoches.teacher.domain.datasources.web.teachers.TeachersDataSource
import com.tangonoches.teacher.domain.datasources.web.ticketCountTypes.ITicketCountTypesDataSource
import com.tangonoches.teacher.domain.datasources.web.ticketCountTypes.TicketCountTypesDataSource
import com.tangonoches.teacher.domain.datasources.web.ticketEventTypes.ITicketEventTypesDataSource
import com.tangonoches.teacher.domain.datasources.web.ticketEventTypes.TicketEventTypesDataSource
import com.tangonoches.teacher.domain.datasources.web.tickets.ITicketsDataSource
import com.tangonoches.teacher.domain.datasources.web.tickets.TicketsDataSource
import com.tangonoches.teacher.domain.repositories.constants.IConstantsRepository
import com.tangonoches.teacher.domain.services.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WebDataSourcesModule {
    @Provides
    @Singleton
    fun provideLoginDataSource(loginService: LoginService): ILoginDataSource =
        LoginDataSource(loginService)

    @Provides
    @Singleton
    fun provideLessonsDataSource(
        consRepository: IConstantsRepository,
        lessonsService: LessonsService
    ): ILessonsDataSource = LessonsDataSource(consRepository, lessonsService)

    @Provides
    @Singleton
    fun provideGroupsDataSource(
        groupsService: GroupsService
    ): IGroupsDataSource = GroupsDataSource(groupsService)

    @Provides
    @Singleton
    fun provideTicketEventTypesDataSource(
        service: TicketEventTypesService
    ): ITicketEventTypesDataSource = TicketEventTypesDataSource(service)

    @Provides
    @Singleton
    fun provideTicketCountTypesDataSource(
        service: TicketCountTypesService
    ): ITicketCountTypesDataSource = TicketCountTypesDataSource(service)

    @Provides
    @Singleton
    fun provideStudentsDataSource(
        service: StudentsService,
        gson: Gson
    ): IStudentsDataSource = StudentsDataSource(service, gson)

    @Provides
    @Singleton
    fun provideTeachersDataSource(
        service: TeachersService
    ): ITeachersDataSource = TeachersDataSource(service)

    @Provides
    @Singleton
    fun provideTicketsDataSource(
        service: TicketsService
    ): ITicketsDataSource = TicketsDataSource(service)
}