package com.tangonoches.teacher.data.responses.ticketEventTypes

import com.tangonoches.teacher.data.models.TicketEventTypeModel

data class TicketEventTypeDto(
    val id: Int = 0,
    val name: String = ""
)

fun TicketEventTypeDto.toModel():TicketEventTypeModel =
    TicketEventTypeModel(
        id = id,
        name = name
    )