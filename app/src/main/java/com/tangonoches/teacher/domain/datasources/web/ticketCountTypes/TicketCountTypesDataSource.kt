package com.tangonoches.teacher.domain.datasources.web.ticketCountTypes

import com.tangonoches.teacher.data.models.TicketCountTypeModel
import com.tangonoches.teacher.data.responses.ticketCoutTypes.toModel
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.services.TicketCountTypesService
import io.reactivex.Single

class TicketCountTypesDataSource(
    private val ticketCountTypesService: TicketCountTypesService
) : ITicketCountTypesDataSource {
    override fun getTicketCountTypes(): Single<List<TicketCountTypeModel>> =
        ticketCountTypesService.getAllTicketCountTypes()
            .map { list -> list.map { dto -> dto.toModel() } }
            .subToThreads()
}