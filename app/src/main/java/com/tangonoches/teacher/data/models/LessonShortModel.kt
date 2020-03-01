package com.tangonoches.teacher.data.models

data class LessonShortModel(
    val name: String,
    val groupId: Long,
    val id: Long = 0,
    val lessonDate: String = "29.02.2020"
) {
}