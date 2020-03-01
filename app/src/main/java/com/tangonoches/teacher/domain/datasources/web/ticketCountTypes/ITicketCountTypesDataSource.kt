package com.tangonoches.teacher.domain.datasources.web.ticketCountTypes

import com.tangonoches.teacher.data.models.TicketCountTypeModel
import io.reactivex.Single

interface ITicketCountTypesDataSource {
    fun getTicketCountTypes(): Single<List<TicketCountTypeModel>>
}