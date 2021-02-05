package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.di.TeacherScope
import com.tangonoches.teacher.domain.datasources.web.groups.IGroupsDataSource
import com.tangonoches.teacher.domain.datasources.web.lessons.ILessonsDataSource
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

@Module
class RepositoriesModule {

//    @Provides
//    @TeacherScope
//    fun provideLoginRepository(loginDataSource: ILoginDataSource, prefsStorage: IPrefsStorage)
//            : ILoginRepository = LoginRepository(
//        loginDataSource,
//        prefsStorage
//    )

    @Provides
    @TeacherScope
    fun provideConstantsRepository(): IConstantsRepository = ConstantsRepository()

    @Provides
    @TeacherScope
    fun provideLessonsRepository(lessonsDataSource: ILessonsDataSource): ILessonsRepository =
        LessonsRepository(lessonsDataSource)

    @Provides
    @TeacherScope
    fun provideGroupsRepository(groupsDataSource: IGroupsDataSource): IGroupsRepository =
        GroupsRepository(groupsDataSource)

    @Provides
    @TeacherScope
    fun provideStudentsRepository(studentsDataSource: IStudentsDataSource): IStudentsRepository =
        StudentsRepository(studentsDataSource)

    @Provides
    @TeacherScope
    fun provideTeachersRepository(dataSource: ITeachersDataSource): ITeachersRepository =
        TeachersRepository(dataSource)

    @Provides
    @TeacherScope
    fun provideTicketsRepository(dataSource: ITicketsDataSource): ITicketsRepository =
        TicketsRepository(dataSource)

    @Provides
    @TeacherScope
    fun provideTicketEventTypesRepository(dataSource: ITicketEventTypesDataSource): ITicketEventTypesRepository =
        TicketEventTypesRepository(dataSource)

    @Provides
    @TeacherScope
    fun provideTicketCountTypesRepository(dataSource: ITicketCountTypesDataSource): ITicketCountTypesRepository =
        TicketCountTypesRepository(dataSource)
}