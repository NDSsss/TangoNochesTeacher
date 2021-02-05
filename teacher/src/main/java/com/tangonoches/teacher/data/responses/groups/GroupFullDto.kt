package com.tangonoches.teacher.data.responses.groups

import com.tangonoches.teacher.data.models.GroupFullModel

data class GroupFullDto(
    val address: String = "",
    val created_at: String? = null,
    val deleted_at: String? = null,
    val first_lesson_time: String = "",
    val id: Long = 0,
    val name: String = "",
    val second_lesson_time: String = "",
    val ticket_event_type_id: Int = 0,
    val updated_at: String? = null
)

fun GroupFullDto.toModel():GroupFullModel=
    GroupFullModel(
        address = this.address,
        createdAt =  this.created_at,
        deletedAt = this.deleted_at,
        name = this.name,
        id = this.id,
        firstLessonTime = this.first_lesson_time,
        secondLessonTime = second_lesson_time,
        ticketEventTypeId = ticket_event_type_id,
        updatedAt = updated_at
    )