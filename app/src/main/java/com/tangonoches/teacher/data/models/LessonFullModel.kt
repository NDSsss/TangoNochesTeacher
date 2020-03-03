package com.tangonoches.teacher.data.models

data class LessonFullModel(
    val createdAt: String? = "",
    val groupId: Long = 0,
    val id: Long = 0,
    val name: String = "",
    val students: List<Long> = listOf(),
    val teachers: List<Long> = listOf(),
    val updatedAt: String? = ""
)