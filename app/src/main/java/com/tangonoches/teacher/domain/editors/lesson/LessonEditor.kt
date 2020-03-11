package com.tangonoches.teacher.domain.editors.lesson

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.models.TeacherShortModel
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class LessonEditor(
    private val teachersRepository: ITeachersRepository,
    private val studentsRepository: IStudentsRepository,
    private val groupsRepository: IGroupsRepository
) : ILessonEditor {

    private val lessonState = BehaviorRelay.create<LessonFullModel>()

    private val teachersState = BehaviorRelay.create<List<TeacherShortModel>>()
    private val studentsState = BehaviorRelay.create<List<StudentShortModel>>()
    private val allTeachersState = BehaviorRelay.create<List<TeacherShortModel>>()
    private val allStudentsState = BehaviorRelay.create<List<StudentShortModel>>()
    private val allgroupsState = BehaviorRelay.create<List<GroupFullModel>>()

    private lateinit var name: String
    private lateinit var group: GroupFullModel

    private val binds = CompositeDisposable()

    init {
        binds.addAll(
            lessonState.flatMap {
                teachersRepository.getAllTeachers().toObservable()
            }.doOnNext { teachers ->
                allTeachersState.accept(teachers)
                val teachersOfLesson = lessonState.value?.teachers ?: listOf()
                teachersState.accept(teachers.filter { teacherShortModel ->
                    teachersOfLesson.contains(
                        teacherShortModel.id
                    )
                })
            }.flatMap {
                studentsRepository.getAllStudents().toObservable()
            }.doOnNext { students ->
                allStudentsState.accept(students)
                val teachersOfLesson = lessonState.value?.students ?: listOf()
                studentsState.accept(students.filter { student ->
                    teachersOfLesson.contains(
                        student.id
                    )
                })
            }.flatMap {
                groupsRepository.getAllGroups().toObservable()
            }.subscribe { groups ->
                allgroupsState.accept(groups)
                val currGroupId = lessonState.value?.groupId ?: 1L
                this.group = groups.find { item -> item.id == currGroupId } ?: groups[0]
            },
            lessonState.subscribe { lesson ->
                this.name = lesson.name
            }
        )
    }

    override fun setLessonToEdit(lesson: LessonFullModel) {
        lessonState.accept(lesson)
    }

    override fun addTeacher(teacher: TeacherShortModel) {
        val teachersOfLesson = teachersState.value ?: listOf()
        if (teachersOfLesson.contains(teacher).not()) {
            teachersOfLesson.plus(teacher)
            teachersState.accept(teachersOfLesson)
        }
    }

    override fun removeTeacher(teacher: TeacherShortModel) {
        val teachersOfLesson = teachersState.value ?: listOf()
        if (teachersOfLesson.contains(teacher)) {
            teachersOfLesson.minus(teacher)
            teachersState.accept(teachersOfLesson)
        }
    }

    override fun addStudent(student: StudentShortModel) {
        val studentsOfLesson = studentsState.value ?: listOf()
        if (studentsOfLesson.contains(student).not()) {
            studentsOfLesson.plus(student)
            studentsState.accept(studentsOfLesson)
        }
    }

    override fun removeStudent(student: StudentShortModel) {
        val studentsOfLesson = studentsState.value ?: listOf()
        if (studentsOfLesson.contains(student)) {
            studentsOfLesson.minus(student)
            studentsState.accept(studentsOfLesson)
        }
    }

    override fun setGroup(group: GroupFullModel) {
        this.group = group
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun getAllTeachersObservable(): Observable<List<TeacherShortModel>> = allTeachersState

    override fun getAllStudentsObservable(): Observable<List<StudentShortModel>> = allStudentsState

    override fun getCurrentLessonTeachersObservable(): Observable<List<TeacherShortModel>> = teachersState

    override fun getCurrentLessonStudentsObservable(): Observable<List<StudentShortModel>> = studentsState
}