package com.tangonoches.teacher.domain.datasources.web.tickets

import com.tangonoches.teacher.data.models.TicketFullClearModel
import com.tangonoches.teacher.data.responses.tickets.toCreateDto
import com.tangonoches.teacher.data.responses.tickets.toModel
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.repositories.constants.ITEMS_ON_PAGE
import com.tangonoches.teacher.domain.services.TicketsService
import io.reactivex.Completable
import io.reactivex.Single

class TicketsDataSource(
    private val ticketsService: TicketsService
) : ITicketsDataSource {

    override fun getTicketsPage(page: Int): Single<List<TicketFullClearModel>> =
        ticketsService.getTicketsPage(page, ITEMS_ON_PAGE).map { response ->
            response.data.map { dto -> dto.toModel() }
        }.subToThreads()

    override fun getTicketById(id: Long): Single<TicketFullClearModel> =
        ticketsService.getTicketById(id).map { dto -> dto.toModel() }
            .subToThreads()

    override fun saveTicket(ticket: TicketFullClearModel): Completable =
        ticketsService.createTicket(ticket.toCreateDto())
            .subToThreads()
}