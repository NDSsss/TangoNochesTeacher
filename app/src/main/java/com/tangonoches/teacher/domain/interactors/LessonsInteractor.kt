package com.tangonoches.teacher.domain.interactors

import com.tangonoches.teacher.data.models.*
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class LessonsInteractor(
    private val lessonsRepository: ILessonsRepository,
    private val groupsRepository: IGroupsRepository,
    private val teachersRepository: ITeachersRepository,
    private val studentsRepository: IStudentsRepository
) : ILessonsInteractor {
    override fun getFirstLessonsPageWithGroups(): Single<Pair<List<GroupFullModel>, List<LessonShortModel>>> =
//    lessonsRepository.getAllLessons(0).map { Pair(listOf<GroupFullModel>(),it) }
        Single.zip(
            groupsRepository.getAllGroups(),
            lessonsRepository.getAllLessons(1),
            BiFunction { t1, t2 -> Pair(t1, t2) }
        )

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

}