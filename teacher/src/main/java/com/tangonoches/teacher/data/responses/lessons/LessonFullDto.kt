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

data class LessonUpdateDto(
    val group_id: Long = 0,
    val id: Long = 0,
    val name: String = "",
    val present_students: List<Long> = listOf(),
    val present_teachers: List<Long> = listOf()
)

fun LessonFullModel.toUpdateDto(): LessonUpdateDto =
    LessonUpdateDto(
        group_id = this.groupId,
        id = this.id,
        name = this.name,
        present_students = this.students,
        present_teachers = this.teachers
    )

data class LessonCreateDto(
    val group_id: Long = 0,
    val name: String = "",
    val present_students: List<Long> = listOf(),
    val present_teachers: List<Long> = listOf()
)

fun LessonFullModel.toCreateDto(): LessonCreateDto =
    LessonCreateDto(
        group_id = this.groupId,
        name = this.name,
        present_students = this.students,
        present_teachers = this.teachers
    )