package com.tangonoches.teacher.domain.repositories.lessons

import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ILessonsRepository {

    fun refreshLessonsObservable(): Observable<List<LessonShortModel>>
    fun refreshLessons(): Completable

    fun getAllLessons(page: Int): Single<List<LessonShortModel>>
    fun getLessonById(id: Long): Single<LessonFullModel>

    fun updateLesson(lesson: LessonFullModel): Completable
    fun createLesson(lesson: LessonFullModel): Completable
    fun deleteLesson(lesson: LessonFullModel): Completable
}