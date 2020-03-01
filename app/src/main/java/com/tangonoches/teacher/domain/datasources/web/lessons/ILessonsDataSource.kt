package com.tangonoches.teacher.domain.datasources.web.lessons

import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.data.responses.lessons.AllLessonsResponse
import io.reactivex.Single

interface ILessonsDataSource {
    fun getAllLessons(page: Int):Single<List<LessonShortModel>>
}