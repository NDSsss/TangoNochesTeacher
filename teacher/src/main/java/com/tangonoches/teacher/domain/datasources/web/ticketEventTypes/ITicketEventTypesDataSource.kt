package com.tangonoches.teacher.domain.datasources.web.ticketEventTypes

import com.tangonoches.teacher.data.models.TicketEventTypeModel
import io.reactivex.Single

interface ITicketEventTypesDataSource {
    fun getTicketEventTypes():Single<List<TicketEventTypeModel>>
}