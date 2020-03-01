package com.tangonoches.teacher.domain.datasources.web.ticketEventTypes

import com.tangonoches.teacher.data.models.TicketEventTypeModel
import com.tangonoches.teacher.data.responses.ticketEventTypes.toModel
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.services.TicketEventTypesService
import io.reactivex.Single

class TicketEventTypesDataSource(
    private val ticketEventTypesService: TicketEventTypesService
): ITicketEventTypesDataSource {
    override fun getTicketEventTypes(): Single<List<TicketEventTypeModel>> =
        ticketEventTypesService.getAllTicketEventTypes()
            .map { list-> list.map { dto-> dto.toModel() } }
            .subToThreads()
}