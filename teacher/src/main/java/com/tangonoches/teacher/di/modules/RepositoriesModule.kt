package com.tangonoches.teacher.di.modules

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
import org.koin.dsl.module

val monolitRepositoriesModule = module {
    factory<IConstantsRepository> {
        ConstantsRepository()
    }
    factory<ILessonsRepository> {
        LessonsRepository(lessonsDataSource = get())
    }
    factory<IGroupsRepository> {
        GroupsRepository(groupsDataSource = get())
    }
    factory<IStudentsRepository> {
        StudentsRepository(studentsDataSource = get())
    }
    factory<ITeachersRepository> {
        TeachersRepository(teachersDataSource = get())
    }
    factory<ITicketsRepository> {
        TicketsRepository(ticketsDataSource = get())
    }
    factory<ITicketEventTypesRepository> {
        TicketEventTypesRepository(ticketEventTypesDataSource = get())
    }
    factory<ITicketCountTypesRepository> {
        TicketCountTypesRepository(ticketCountTypesDataSource = get())
    }
}