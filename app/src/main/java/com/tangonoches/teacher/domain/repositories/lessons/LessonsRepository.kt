package com.tangonoches.teacher.domain.repositories.lessons

import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.domain.datasources.web.lessons.ILessonsDataSource
import io.reactivex.Single

class LessonsRepository(
    private val lessonsDataSource: ILessonsDataSource
) : ILessonsRepository {
    override fun getAllLessons(page: Int): Single<List<LessonShortModel>> =
        lessonsDataSource.getAllLessons(page)
}