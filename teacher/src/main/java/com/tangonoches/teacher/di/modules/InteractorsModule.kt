package com.tangonoches.teacher.di.modules

import com.tangonoches.teacher.domain.interactors.ILessonsInteractor
import com.tangonoches.teacher.domain.interactors.LessonsInteractor
import com.tangonoches.teacher.domain.interactors.students.IStudentsInteractor
import com.tangonoches.teacher.domain.interactors.students.StudentsInteractor
import com.tangonoches.teacher.domain.interactors.tickets.ITicketsInteractor
import com.tangonoches.teacher.domain.interactors.tickets.TicketsInteractor
import org.koin.dsl.module

val monolitInteractorsModule = module {
    factory<ILessonsInteractor> {
        LessonsInteractor(
            lessonsRepository = get(),
            groupsRepository = get(),
            teachersRepository = get(),
            studentsRepository = get(),
        )
    }
    factory<IStudentsInteractor> {
        StudentsInteractor(
            studentsRepository = get(),
        )
    }
    factory<ITicketsInteractor> {
        TicketsInteractor(
            ticketsRepository = get(),
            ticketEventTypesRepository = get(),
            ticketCountTypesRepositoryRepository = get(),
            studentsRepository = get(),
            teacherssRepository = get(),
        )
    }
}