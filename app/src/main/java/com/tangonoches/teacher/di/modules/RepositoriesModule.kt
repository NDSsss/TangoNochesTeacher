package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.datasources.prefs.IPrefsStorage
import com.tangonoches.teacher.domain.datasources.web.groups.IGroupsDataSource
import com.tangonoches.teacher.domain.datasources.web.lessons.ILessonsDataSource
import com.tangonoches.teacher.domain.datasources.web.login.ILoginDataSource
import com.tangonoches.teacher.domain.datasources.web.students.IStudentsDataSource
import com.tangonoches.teacher.domain.datasources.web.teachers.ITeachersDataSource
import com.tangonoches.teacher.domain.datasources.web.ticketCountTypes.ITicketCountTypesDataSource
import com.tangonoches.teacher.domain.datasources.web.ticketEventTypes.ITicketEventTypesDataSource
import com.tangonoches.teacher.domain.datasources.web.tickets.ITicketsDataSource
import com.tangonoches.teacher.domain.repositories.constants.ConstantsRepository
import com.tangonoches.teacher.domain.repositories.constants.IConstantsRepository
import com.tangonoches.teacher.domain.repositories.groups.GroupsRepository
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.domain.repositories.lessons.LessonsRepository
import com.tangonoches.teacher.domain.repositories.login.ILoginRepository
import com.tangonoches.teacher.domain.repositories.login.LoginRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.students.StudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import com.tangonoches.teacher.domain.repositories.teachers.TeachersRepository
import com.tangonoches.teacher.domain.repositories.ticketCountTypes.ITicketCountTypesRepository
import com.tangonoches.teacher.domain.repositories.ticketCountTypes.TicketCountTypesRepository
import com.tangonoches.teacher.domain.repositories.ticketEventTypes.ITicketEventTypesRepository
import com.tangonoches.teacher.domain.repositories.ticketEventTypes.TicketEventTypesRepository
import com.tangonoches.teacher.domain.repositories.tickets.ITicketsRepository
import com.tangonoches.teacher.domain.repositories.tickets.TicketsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginDataSource: ILoginDataSource, prefsStorage: IPrefsStorage)
            : ILoginRepository = LoginRepository(
        loginDataSource,
        prefsStorage
    )

    @Provides
    @Singleton
    fun provideConstantsRepository(): IConstantsRepository = ConstantsRepository()

    @Provides
    @Singleton
    fun provideLessonsRepository(lessonsDataSource: ILessonsDataSource): ILessonsRepository =
        LessonsRepository(lessonsDataSource)

    @Provides
    @Singleton
    fun provideGroupsRepository(groupsDataSource: IGroupsDataSource): IGroupsRepository =
        GroupsRepository(groupsDataSource)

    @Provides
    @Singleton
    fun provideStudentsRepository(studentsDataSource: IStudentsDataSource): IStudentsRepository =
        StudentsRepository(studentsDataSource)

    @Provides
    @Singleton
    fun provideTeachersRepository(dataSource: ITeachersDataSource): ITeachersRepository =
        TeachersRepository(dataSource)

    @Provides
    @Singleton
    fun provideTicketsRepository(dataSource: ITicketsDataSource): ITicketsRepository =
        TicketsRepository(dataSource)

    @Provides
    @Singleton
    fun provideTicketEventTypesRepository(dataSource: ITicketEventTypesDataSource): ITicketEventTypesRepository =
        TicketEventTypesRepository(dataSource)

    @Provides
    @Singleton
    fun provideTicketCountTypesRepository(dataSource: ITicketCountTypesDataSource): ITicketCountTypesRepository =
        TicketCountTypesRepository(dataSource)
}