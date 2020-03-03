package com.tangonoches.teacher.data.responses.lessons

import com.tangonoches.teacher.data.models.LessonFullModel

data class LessonFullDto(
    val created_at: String? = "",
    val group_id: Long = 0,
    val id: Long = 0,
    val name: String = "",
    val students: List<Long> = listOf(),
    val teachers: List<Long> = listOf(),
    val updated_at: String? = ""
)

fun LessonFullDto.toModel(): LessonFullModel =
    LessonFullModel(
        createdAt = created_at,
        id = id,
        name = name,
        updatedAt = updated_at,
        groupId = group_id,
        students = students,
        teachers = teachers
    )