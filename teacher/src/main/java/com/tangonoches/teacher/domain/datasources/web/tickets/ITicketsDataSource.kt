package com.tangonoches.teacher.domain.datasources.web.tickets

import com.tangonoches.teacher.data.models.TicketFullClearModel
import io.reactivex.Completable
import io.reactivex.Single

interface ITicketsDataSource {

    fun getTicketsPage(page:Int):Single<List<TicketFullClearModel>>
    fun getTicketById(id:Long):Single<TicketFullClearModel>
    fun saveTicket(ticket: TicketFullClearModel):Completable
}