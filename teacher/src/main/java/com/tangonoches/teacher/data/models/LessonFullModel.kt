package com.tangonoches.teacher.data.models

import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID

data class LessonFullModel(
    val createdAt: String? = "",
    val groupId: Long = DEFAULT_ID,
    val id: Long = DEFAULT_ID,
    val name: String = "",
    val students: List<Long> = listOf(),
    val teachers: List<Long> = listOf(),
    val updatedAt: String? = ""
)