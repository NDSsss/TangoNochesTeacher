package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.ticketCoutTypes.TicketCountTypeDto
import io.reactivex.Single
import retrofit2.http.GET

interface TicketCountTypesService {
    @GET("")
    fun getAllTicketCountTypes(): Single<List<TicketCountTypeDto>>
}