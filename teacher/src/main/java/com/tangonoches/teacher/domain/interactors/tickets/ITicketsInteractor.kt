package com.tangonoches.teacher.domain.interactors.tickets

import com.tangonoches.teacher.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ITicketsInteractor {
    fun getTicketsPage(page: Int): Single<List<TicketFullFilledModel>>
    fun getTicketById(id: Long): Single<TicketFullClearModel>
    fun createTicket(ticket: TicketFullClearModel): Completable

    fun getRefreshObservable():Observable<Unit>

    fun getAllStudents(): Single<List<StudentShortModel>>
    fun getAllTeachers(): Single<List<TeacherShortModel>>
    fun getAllEventTypes(): Single<List<TicketEventTypeModel>>
    fun getAllCountTypes(): Single<List<TicketCountTypeModel>>
}