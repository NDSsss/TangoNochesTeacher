package com.tangonoches.teacher.domain.datasources.web.ticketEventTypes

import com.tangonoches.teacher.data.models.TicketEventTypeModel
import com.tangonoches.teacher.data.responses.ticketEventTypes.toModel
import com.tangonoches.teacher.domain.services.TicketEventTypesService
import io.reactivex.Single
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.utils.subToThreads

class TicketEventTypesDataSource(
    private val schedulers: ISchedulers,
    private val ticketEventTypesService: TicketEventTypesService
): ITicketEventTypesDataSource {
    override fun getTicketEventTypes(): Single<List<TicketEventTypeModel>> =
        ticketEventTypesService.getAllTicketEventTypes()
            .map { list-> list.map { dto-> dto.toModel() } }
            .subToThreads(schedulers)
}