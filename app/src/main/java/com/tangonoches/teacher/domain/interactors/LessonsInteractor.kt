package com.tangonoches.teacher.domain.interactors

import com.tangonoches.teacher.data.models.*
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class LessonsInteractor(
    private val lessonsRepository: ILessonsRepository,
    private val groupsRepository: IGroupsRepository,
    private val teachersRepository: ITeachersRepository,
    private val studentsRepository: IStudentsRepository
) : ILessonsInteractor {

    override fun refreshLessons(): Completable = lessonsRepository.refreshLessons()

    override fun lessonsRefreshObservable(): Observable<List<LessonShortModel>> =
        lessonsRepository.refreshLessonsObservable()

    override fun getLessonsPage(page: Int): Single<List<LessonShortModel>> =
        lessonsRepository.getAllLessons(page)

    override fun getLessonById(id: Long): Single<LessonFullModel> =
        lessonsRepository.getLessonById(id)

    override fun getGroups(): Single<List<GroupFullModel>> =
        groupsRepository.getAllGroups()

    override fun getTeachers(): Single<List<TeacherShortModel>> =
        teachersRepository.getAllTeachers()

    override fun getStudents(): Single<List<StudentShortModel>> =
        studentsRepository.getAllStudents()

    override fun updateLesson(lesson: LessonFullModel): Completable =
        lessonsRepository.updateLesson(lesson)

    override fun createLesson(lesson: LessonFullModel): Completable =
        lessonsRepository.createLesson(lesson)
}