package com.tangonoches.teacher.di.modules

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
import org.koin.dsl.module

val monolitWebDataSourceModule = module {
    factory<ILessonsDataSource> {
        LessonsDataSource(schedulers = get(), constantsRepository = get(), lessonsService = get())
    }
    factory<IGroupsDataSource> {
        GroupsDataSource(schedulers = get(), groupsService = get())
    }
    factory<ITicketEventTypesDataSource> {
        TicketEventTypesDataSource(schedulers = get(), ticketEventTypesService = get())
    }
    factory<ITicketCountTypesDataSource> {
        TicketCountTypesDataSource(schedulers = get(), ticketCountTypesService = get())
    }
    factory<IStudentsDataSource> {
        StudentsDataSource(schedulers = get(), studentsService = get(), gson = get())
    }
    factory<ITeachersDataSource> {
        TeachersDataSource(schedulers = get(), teachersService = get())
    }
    factory<ITicketsDataSource> {
        TicketsDataSource(schedulers = get(), ticketsService = get())
    }
}