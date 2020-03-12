package com.tangonoches.teacher.domain.repositories.lessons

import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.LessonShortModel
import com.tangonoches.teacher.domain.datasources.web.lessons.ILessonsDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class LessonsRepository(
    private val lessonsDataSource: ILessonsDataSource
) : ILessonsRepository {

    private val refreshLessonsAction = PublishRelay.create<List<LessonShortModel>>()

    override fun refreshLessons(): Completable =
        Completable.fromSingle(
            getAllLessons(1).doOnSuccess { lessons ->
                refreshLessonsAction.accept(lessons)
            }
        )

    override fun refreshLessonsObservable(): Observable<List<LessonShortModel>> =
        refreshLessonsAction

    override fun getAllLessons(page: Int): Single<List<LessonShortModel>> =
        lessonsDataSource.getAllLessons(page)

    override fun getLessonById(id: Long): Single<LessonFullModel> =
        lessonsDataSource.getLessonById(id)

    override fun updateLesson(lesson: LessonFullModel): Completable =
        lessonsDataSource.updateLesson(lesson)

    override fun createLesson(lesson: LessonFullModel): Completable =
        lessonsDataSource.createLesson(lesson)
}