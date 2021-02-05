package com.tangonoches.teacher.data.responses.lessons

import com.tangonoches.teacher.data.models.LessonShortModel

data class ShortLessonDto(val id:Long, val name: String, val group_id: Long) {
}

fun ShortLessonDto.toModel(): LessonShortModel =
    LessonShortModel(
        id = id,
        name = this.name,
        groupId = this.group_id
    )