package com.tangonoches.teacher.data.responses.lessons

import com.tangonoches.teacher.data.models.LessonShortModel

data class ShortLessonDto(val name: String, val group_id: Long) {
}

fun ShortLessonDto.toModel(): LessonShortModel =
    LessonShortModel(
        name = this.name,
        groupId = this.group_id
    )