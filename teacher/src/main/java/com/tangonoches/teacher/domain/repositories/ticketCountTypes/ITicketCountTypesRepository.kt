package com.tangonoches.teacher.domain.repositories.ticketCountTypes

import com.tangonoches.teacher.data.models.TicketCountTypeModel
import io.reactivex.Single

interface ITicketCountTypesRepository {
    fun getTicketCountTypes():Single<List<TicketCountTypeModel>>
}