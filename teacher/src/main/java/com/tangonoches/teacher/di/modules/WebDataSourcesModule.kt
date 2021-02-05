package com.tangonoches.teacher.di.modules

import com.google.gson.Gson
import com.tangonoches.teacher.di.TeacherScope
import com.tangonoches.teacher.domain.datasources.web.groups.GroupsDataSource
import com.tangonoches.teacher.domain.datasources.web.groups.IGroupsDataSource
import com.tangonoches.teacher.domain.datasources.web.lessons.ILessonsDataSource
import com.tangonoches.teacher.domain.datasources.web.lessons.LessonsDataSource
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
import ru.nds.common.rx.ISchedulers

@Module
class WebDataSourcesModule {
//    @Provides
//    @TeacherScope
//    fun provideLoginDataSource(loginService: LoginService): ILoginDataSource =
//        LoginDataSource(loginService)

    @Provides
    @TeacherScope
    fun provideLessonsDataSource(
        schedulers: ISchedulers,
        consRepository: IConstantsRepository,
        lessonsService: LessonsService
    ): ILessonsDataSource = LessonsDataSource(schedulers, consRepository, lessonsService)

    @Provides
    @TeacherScope
    fun provideGroupsDataSource(
        schedulers: ISchedulers,
        groupsService: GroupsService
    ): IGroupsDataSource = GroupsDataSource(schedulers, groupsService)

    @Provides
    @TeacherScope
    fun provideTicketEventTypesDataSource(
        schedulers: ISchedulers,
        service: TicketEventTypesService
    ): ITicketEventTypesDataSource = TicketEventTypesDataSource(schedulers, service)

    @Provides
    @TeacherScope
    fun provideTicketCountTypesDataSource(
        schedulers: ISchedulers,
        service: TicketCountTypesService
    ): ITicketCountTypesDataSource = TicketCountTypesDataSource(schedulers, service)

    @Provides
    @TeacherScope
    fun provideStudentsDataSource(
        schedulers: ISchedulers,
        service: StudentsService,
        gson: Gson
    ): IStudentsDataSource = StudentsDataSource(schedulers, service, gson)

    @Provides
    @TeacherScope
    fun provideTeachersDataSource(
        schedulers: ISchedulers,
        service: TeachersService
    ): ITeachersDataSource = TeachersDataSource(schedulers, service)

    @Provides
    @TeacherScope
    fun provideTicketsDataSource(
        schedulers: ISchedulers,
        service: TicketsService
    ): ITicketsDataSource = TicketsDataSource(schedulers, service)
}