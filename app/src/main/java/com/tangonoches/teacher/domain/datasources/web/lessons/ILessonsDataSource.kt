package com.tangonoches.teacher.domain.datasources.web.lessons

import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import io.reactivex.Completable
import io.reactivex.Single

interface ILessonsDataSource {
    fun getAllLessons(page: Int): Single<List<LessonShortModel>>

    fun getLessonById(id: Long): Single<LessonFullModel>

    fun updateLesson(lesson: LessonFullModel): Completable

    fun createLesson(lesson: LessonFullModel): Completable
}