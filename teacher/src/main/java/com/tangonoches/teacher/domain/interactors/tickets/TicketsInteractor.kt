package com.tangonoches.teacher.domain.interactors.tickets

import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.*
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import com.tangonoches.teacher.domain.repositories.ticketCountTypes.ITicketCountTypesRepository
import com.tangonoches.teacher.domain.repositories.ticketEventTypes.ITicketEventTypesRepository
import com.tangonoches.teacher.domain.repositories.tickets.ITicketsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function5

class TicketsInteractor(
    private val ticketsRepository: ITicketsRepository,
    private val ticketEventTypesRepository: ITicketEventTypesRepository,
    private val ticketCountTypesRepositoryRepository: ITicketCountTypesRepository,
    private val studentsRepository: IStudentsRepository,
    private val teacherssRepository: ITeachersRepository
) : ITicketsInteractor {

    private val refreshTicketsRelay = PublishRelay.create<Unit>()

    override fun getTicketsPage(page: Int): Single<List<TicketFullFilledModel>> =
        Single.zip(
            ticketsRepository.getTicketsPage(page),
            studentsRepository.getAllStudents(),
            teacherssRepository.getAllTeachers(),
            ticketEventTypesRepository.getTicketEventTypes(),
            ticketCountTypesRepositoryRepository.getTicketCountTypes(),
            Function5 { tickets: List<TicketFullClearModel>,
                        students: List<StudentShortModel>,
                        teachers: List<TeacherShortModel>,
                        eventTypes: List<TicketEventTypeModel>,
                        coutnTypes: List<TicketCountTypeModel> ->
                return@Function5 tickets.map { ticket ->
                    ticket.fill(students, teachers, eventTypes, coutnTypes)
                }
            }
        )

    override fun getTicketById(id: Long): Single<TicketFullClearModel> =
        ticketsRepository.getTicketById(id)

    override fun createTicket(ticket: TicketFullClearModel): Completable =
        ticketsRepository.createTicket(ticket)
            .doOnComplete { refreshTicketsRelay.accept(Unit) }

    override fun getRefreshObservable(): Observable<Unit> =
        refreshTicketsRelay

    override fun getAllStudents(): Single<List<StudentShortModel>> =
        studentsRepository.getAllStudents()

    override fun getAllTeachers(): Single<List<TeacherShortModel>> =
        teacherssRepository.getAllTeachers()

    override fun getAllEventTypes(): Single<List<TicketEventTypeModel>> =
        ticketEventTypesRepository.getTicketEventTypes()

    override fun getAllCountTypes(): Single<List<TicketCountTypeModel>> =
        ticketCountTypesRepositoryRepository.getTicketCountTypes()

    fun TicketFullClearModel.fill(
        students: List<StudentShortModel>,
        teachers: List<TeacherShortModel>,
        eventTypes: List<TicketEventTypeModel>,
        countTypes: List<TicketCountTypeModel>
    ): TicketFullFilledModel =
        TicketFullFilledModel(
            extraLessons = extraLessons,
            ticketStartDate = ticketStartDate,
            ticketEndDate = ticketEndDate,
            ticketBought_date = ticketBought_date,
            teacher = teachers.firstOrNull { teach -> teach.id == teacherId } ?: TeacherShortModel(
                name = "Не найден"
            ),
            isNullify = isNullify,
            isInPair = isInPair,
            id = id,
            student = students.firstOrNull { stud -> stud.id == studentId } ?: StudentShortModel(
                name = "Не найден"
            ),
            ticketCountType = countTypes.firstOrNull { type -> type.id == ticketCountTypeId }
                ?: TicketCountTypeModel(name = "Не найден"),
            ticketEventType = eventTypes.firstOrNull { type -> type.id == ticketEventTypeId }
                ?: TicketEventTypeModel(name = "Не найден")
        )

}