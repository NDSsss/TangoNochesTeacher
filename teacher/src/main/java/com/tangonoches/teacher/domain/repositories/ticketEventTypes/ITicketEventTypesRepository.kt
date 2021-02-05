package com.tangonoches.teacher.domain.repositories.ticketEventTypes

import com.tangonoches.teacher.data.models.TicketEventTypeModel
import io.reactivex.Single

interface ITicketEventTypesRepository {
    fun getTicketEventTypes():Single<List<TicketEventTypeModel>>
}