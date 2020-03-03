package com.tangonoches.teacher.domain.repositories.lessons

import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import io.reactivex.Single

interface ILessonsRepository {
    fun getAllLessons(page: Int): Single<List<LessonShortModel>>
    fun getLessonById(id: Long): Single<LessonFullModel>
}