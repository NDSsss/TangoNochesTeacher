package com.tangonoches.teacher.domain.interactors

import com.tangonoches.teacher.data.models.*
import io.reactivex.Single

interface ILessonsInteractor {
    fun getFirstLessonsPageWithGroups(): Single<Pair<List<GroupFullModel>, List<LessonShortModel>>>
    fun getLessonsPage(page: Int): Single<List<LessonShortModel>>
    fun getLessonById(id: Long): Single<LessonFullModel>

    fun getGroups(): Single<List<GroupFullModel>>
    fun getTeachers(): Single<List<TeacherShortModel>>
    fun getStudents(): Single<List<StudentShortModel>>
}