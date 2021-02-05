package com.tangonoches.teacher.domain.datasources.web.ticketCountTypes

import com.tangonoches.teacher.data.models.TicketCountTypeModel
import com.tangonoches.teacher.data.responses.ticketCoutTypes.toModel
import com.tangonoches.teacher.domain.services.TicketCountTypesService
import io.reactivex.Single
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.utils.subToThreads

class TicketCountTypesDataSource(
    private val schedulers: ISchedulers,
    private val ticketCountTypesService: TicketCountTypesService
) : ITicketCountTypesDataSource {
    override fun getTicketCountTypes(): Single<List<TicketCountTypeModel>> =
        ticketCountTypesService.getAllTicketCountTypes()
            .map { list -> list.map { dto -> dto.toModel() } }
            .subToThreads(schedulers)
}