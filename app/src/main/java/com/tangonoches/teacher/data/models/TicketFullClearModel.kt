package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import java.util.*

data class TicketFullClearModel(
    val extraLessons: Int = 0,
    val id: Long = DEFAULT_ID,
    val isInPair: Boolean = false,
    val isNullify: Boolean = false,
    val studentId: Long = DEFAULT_ID,
    val teacherId: Long = DEFAULT_ID,
    val ticketBought_date: Date = Date(),
    val ticketCountTypeId: Long = DEFAULT_ID,
    val ticketEndDate: Date = Date(),
    val ticketEventTypeId: Long = DEFAULT_ID,
    val ticketStartDate: Date = Date()
)

data class TicketFullFilledModel(
    val extraLessons: Int = 0,
    val id: Long = DEFAULT_ID,
    val isInPair: Boolean = false,
    val isNullify: Boolean = false,
    val student: StudentShortModel = StudentShortModel(),
    val teacher: TeacherShortModel = TeacherShortModel(),
    val ticketBought_date: Date = Date(),
    val ticketCountType: TicketCountTypeModel = TicketCountTypeModel(),
    val ticketEndDate:Date = Date(),
    val ticketEventType: TicketEventTypeModel = TicketEventTypeModel(),
    val ticketStartDate: Date = Date()
)