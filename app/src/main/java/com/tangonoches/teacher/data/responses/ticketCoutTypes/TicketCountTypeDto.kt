package com.tangonoches.teacher.data.responses.ticketCoutTypes

import com.tangonoches.teacher.data.models.TicketCountTypeModel

data class TicketCountTypeDto(
    val id: Int = 0,
    val lessons_count: Int = 0,
    val name: String = "",
    val price: Double = 0.0
)

fun TicketCountTypeDto.toModel():TicketCountTypeModel =
    TicketCountTypeModel(
        id = id,
        name = name,
        lessonsCount = lessons_count,
        price = price
    )