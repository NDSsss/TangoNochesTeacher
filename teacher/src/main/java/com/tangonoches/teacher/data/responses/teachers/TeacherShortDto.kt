package com.tangonoches.teacher.data.responses.teachers

import com.tangonoches.teacher.data.models.TeacherShortModel

data class TeacherShortDto(
    val id: Long = 0,
    val name: String = ""
)

fun TeacherShortDto.toModel(): TeacherShortModel =
    TeacherShortModel(
        id = id,
        name = name
    )