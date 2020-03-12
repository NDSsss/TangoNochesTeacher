package com.tangonoches.teacher.data.models

const val DEFAULT_LESSON_ID = -1L

data class LessonFullModel(
    val createdAt: String? = "",
    val groupId: Long = 0,
    val id: Long = DEFAULT_LESSON_ID,
    val name: String = "",
    val students: List<Long> = listOf(),
    val teachers: List<Long> = listOf(),
    val updatedAt: String? = ""
)