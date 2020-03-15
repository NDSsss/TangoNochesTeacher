package com.tangonoches.teacher.domain.repositories.tickets

import com.tangonoches.teacher.data.models.TicketFullClearModel
import com.tangonoches.teacher.domain.datasources.web.tickets.ITicketsDataSource
import io.reactivex.Completable
import io.reactivex.Single

class TicketsRepository(
    private val ticketsDataSource: ITicketsDataSource
) : ITicketsRepository {

    override fun getTicketsPage(page: Int): Single<List<TicketFullClearModel>> =
        ticketsDataSource.getTicketsPage(page)

    override fun getTicketById(id: Long): Single<TicketFullClearModel> =
        ticketsDataSource.getTicketById(id)

    override fun createTicket(ticket: TicketFullClearModel): Completable =
        ticketsDataSource.saveTicket(ticket)

}