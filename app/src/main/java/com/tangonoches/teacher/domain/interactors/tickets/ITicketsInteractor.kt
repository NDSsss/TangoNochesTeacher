package com.tangonoches.teacher.domain.interactors.tickets

import com.tangonoches.teacher.data.models.TicketFullClearModel
import com.tangonoches.teacher.data.models.TicketFullFilledModel
import io.reactivex.Completable
import io.reactivex.Single

interface ITicketsInteractor {
    fun getTicketsPage(page: Int): Single<List<TicketFullFilledModel>>
    fun getTicketById(id: Long): Single<TicketFullClearModel>
    fun createTicket(ticket: TicketFullClearModel): Completable
}