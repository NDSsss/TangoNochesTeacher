package com.tangonoches.teacher.domain.repositories.tickets

import com.tangonoches.teacher.data.models.TicketFullClearModel
import io.reactivex.Completable
import io.reactivex.Single

interface ITicketsRepository {
    fun getTicketsPage(page: Int): Single<List<TicketFullClearModel>>
    fun getTicketById(id: Long): Single<TicketFullClearModel>
    fun createTicket(ticket: TicketFullClearModel): Completable
}