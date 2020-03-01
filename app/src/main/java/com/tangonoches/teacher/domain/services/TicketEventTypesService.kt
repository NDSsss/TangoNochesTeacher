package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.ticketEventTypes.TicketEventTypeDto
import io.reactivex.Single
import retrofit2.http.GET

interface TicketEventTypesService {
    @GET("")
    fun getAllTicketEventTypes():Single<List<TicketEventTypeDto>>
}