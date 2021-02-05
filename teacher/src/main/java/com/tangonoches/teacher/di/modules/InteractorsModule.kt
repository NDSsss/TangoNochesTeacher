package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.di.TeacherScope
import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.domain.interactors.LessonsInteractor
import com.tangonoches.teacher.domain.interactors.students.IStudentsInteractor
import com.tangonoches.teacher.domain.interactors.students.StudentsInteractor
import com.tangonoches.teacher.domain.interactors.tickets.ITicketsInteractor
import com.tangonoches.teacher.domain.interactors.tickets.TicketsInteractor
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import com.tangonoches.teacher.domain.repositories.ticketCountTypes.ITicketCountTypesRepository
import com.tangonoches.teacher.domain.repositories.ticketEventTypes.ITicketEventTypesRepository
import com.tangonoches.teacher.domain.repositories.tickets.ITicketsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {
    @Provides
    @TeacherScope
    fun provideLessonsInteractor(
        lessonsRepository: ILessonsRepository,
        groupsRepository: IGroupsRepository,
        teachersRepository: ITeachersRepository,
        studentsRepository: IStudentsRepository
    ): ILessonsInteractor =
        LessonsInteractor(
            lessonsRepository,
            groupsRepository,
            teachersRepository,
            studentsRepository
        )

    @Provides
    @TeacherScope
    fun provideStudentsInteractor(
        studentsRepository: IStudentsRepository
    ): IStudentsInteractor =
        StudentsInteractor(
            studentsRepository
        )

    @Provides
    @TeacherScope
    fun provideTicketsInteractor(
        ticketsRepository: ITicketsRepository,
        ticketEventTypesRepository: ITicketEventTypesRepository,
        ticketCountTypesRepository: ITicketCountTypesRepository,
        studentsRepository: IStudentsRepository,
        teachersRepository: ITeachersRepository
    ): ITicketsInteractor =
        TicketsInteractor(
            ticketsRepository,
            ticketEventTypesRepository,
            ticketCountTypesRepository,
            studentsRepository,
            teachersRepository
        )
}