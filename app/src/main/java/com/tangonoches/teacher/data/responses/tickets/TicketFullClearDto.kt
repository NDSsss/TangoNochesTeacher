package com.tangonoches.teacher.data.responses.tickets

import com.tangonoches.teacher.data.models.TicketFullClearModel
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import java.util.*

data class TicketFullClearDto(
    val extra_lessons: Int = 0,
    val id: Long = DEFAULT_ID,
    val is_in_pair: Boolean = false,
    val is_nullify: Boolean = false,
    val student_id: Long = DEFAULT_ID,
    val teacher_id: Long = DEFAULT_ID,
    val ticket_bought_date: Date = Date(),
    val ticket_count_type_id: Long = DEFAULT_ID,
    val ticket_end_date: Date = Date(),
    val ticket_event_type_id: Long = DEFAULT_ID,
    val ticket_start_date: Date = Date()
)

fun TicketFullClearDto.toModel(): TicketFullClearModel =
    TicketFullClearModel(
        extraLessons = extra_lessons,
        id = id,
        isInPair = is_in_pair,
        isNullify = is_nullify,
        studentId = student_id,
        teacherId = teacher_id,
        ticketBought_date = ticket_bought_date,
        ticketCountTypeId = ticket_count_type_id,
        ticketEventTypeId = ticket_event_type_id,
        ticketEndDate = ticket_end_date,
        ticketStartDate = ticket_start_date
    )

data class TicketCreateDto(
    val extra_lessons: Int = 0,
    val is_in_pair: Boolean = false,
    val is_nullify: Boolean = false,
    val student_id: Long = DEFAULT_ID,
    val teacher_id: Long = DEFAULT_ID,
    val ticket_count_type_id: Long = DEFAULT_ID,
    val ticket_end_date: Date = Date(),
    val ticket_event_type_id: Long = DEFAULT_ID,
    val ticket_start_date: Date = Date()
)

fun TicketFullClearModel.toCreateDto(): TicketCreateDto =
    TicketCreateDto(
        extra_lessons = extraLessons,
        is_in_pair = isInPair,
        is_nullify = isNullify,
        student_id = studentId,
        teacher_id = teacherId,
        ticket_count_type_id = ticketCountTypeId,
        ticket_end_date = ticketEndDate,
        ticket_event_type_id = ticketEventTypeId,
        ticket_start_date = ticketStartDate
    )