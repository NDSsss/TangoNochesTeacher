package com.tangonoches.teacher.domain.interactors

import com.tangonoches.teacher.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ILessonsInteractor {
    fun getLessonsPage(page: Int): Single<List<LessonShortModel>>
    fun getLessonById(id: Long): Single<LessonFullModel>

    fun refreshLessons(): Completable
    fun lessonsRefreshObservable(): Observable<List<LessonShortModel>>

    fun getGroups(): Single<List<GroupFullModel>>
    fun getTeachers(): Single<List<TeacherShortModel>>
    fun getStudents(): Single<List<StudentShortModel>>

    fun updateLesson(lesson: LessonFullModel): Completable
    fun createLesson(lesson: LessonFullModel): Completable
    fun deleteLesson(lesson: LessonFullModel): Completable
}