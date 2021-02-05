package com.tangonoches.teacher.domain.editors.lesson

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.models.TeacherShortModel
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import com.tangonoches.teacher.domain.repositories.groups.IGroupsRepository
import com.tangonoches.teacher.domain.repositories.lessons.ILessonsRepository
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import com.tangonoches.teacher.domain.repositories.teachers.ITeachersRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class LessonEditor(
    private val teachersRepository: ITeachersRepository,
    private val studentsRepository: IStudentsRepository,
    private val groupsRepository: IGroupsRepository,
    private val lessonsRepository: ILessonsRepository
) : ILessonEditor {

    private val lessonState = PublishRelay.create<LessonFullModel>()

    private var lesson: LessonFullModel? = null

    private val teachersState = BehaviorRelay.create<List<TeacherShortModel>>()
    private val studentsState = BehaviorRelay.create<List<StudentShortModel>>()
    private val allTeachersState = BehaviorRelay.create<List<TeacherShortModel>>()
    private val allStudentsState = BehaviorRelay.create<List<StudentShortModel>>()
    private val allgroupsState = BehaviorRelay.create<List<GroupFullModel>>()

    private lateinit var name: String

    private val binds = CompositeDisposable()

    init {
        binds.addAll(
            lessonState
                .doOnNext {
                    lesson = it
                }
                .flatMap {
                    teachersRepository.getAllTeachers().toObservable()
                }.doOnNext { teachers ->
                    val teachersOfLesson =
                        lesson?.teachers ?: throw NullPointerException("Lesson can not be null")
                    allTeachersState.accept(teachers)
                    teachersState.accept(teachers.map {
                        it.setIsSelected(
                            teachersOfLesson.contains(
                                it.id
                            )
                        )
                    })
                }.flatMap {
                    studentsRepository.getAllStudents().toObservable()
                }.doOnNext { students ->
                    allStudentsState.accept(students)
                    val studentsOfLesson = lesson?.students ?: listOf()
                    studentsState.accept(students.map {
                        it.setIsSelected(
                            studentsOfLesson.contains(
                                it.id
                            )
                        )
                    })
                }.flatMap {
                    groupsRepository.getAllGroups().toObservable()
                }.subscribe { groups ->
                    Log.d("APP_TAG", "${this::class.java.simpleName} groupState subscribe $groups")
                    val currGroupId = lesson?.groupId ?: DEFAULT_ID
                    allgroupsState.accept(groups.map { item -> item.setIsSelected(item.id == currGroupId) })
                    if (currGroupId == DEFAULT_ID) {
                        groupSelected(groups[0].id)
                    }
                },
            lessonState.subscribe { lesson ->
                this.name = lesson.name
            }
        )
    }

    override fun setLessonToEdit(lesson: LessonFullModel) {
        lessonState.accept(lesson)
    }

    override fun addTeacher(teacherId: Long) {
        val teachersOfLesson = teachersState.value ?: listOf()
        var foundItem = false
        val updatedList = teachersOfLesson.map {
            if (it.id == teacherId) {
                foundItem = true
                it.setIsSelected(true)
            } else {
                it
            }
        }
        if (foundItem) {
            teachersState.accept(updatedList)
        }

    }

    override fun removeTeacher(teacherId: Long) {
        val teachersOfLesson = teachersState.value ?: listOf()
        var foundItem = false
        val updatedList = teachersOfLesson.map {
            if (it.id == teacherId) {
                foundItem = true
                it.setIsSelected(false)
            } else {
                it
            }
        }
        if (foundItem) {
            teachersState.accept(updatedList)
        }
    }

    override fun addStudent(studentId: Long) {
        val studentsOfLesson = studentsState.value ?: listOf()
        var foundItem = false
        val updatedList = studentsOfLesson.map {
            if (it.id == studentId) {
                foundItem = true
                it.setIsSelected(true)
            } else {
                it
            }
        }
        if (foundItem) {
            studentsState.accept(updatedList)
        }
    }

    override fun removeStudent(studentId: Long) {
        val studentsOfLesson = studentsState.value ?: listOf()
        var foundItem = false
        val updatedList = studentsOfLesson.map {
            if (it.id == studentId) {
                foundItem = true
                it.setIsSelected(false)
            } else {
                it
            }
        }
        if (foundItem) {
            studentsState.accept(updatedList)
        }
    }

    override fun groupSelected(groupId: Long) {
        val currentGroups =
            allgroupsState.value ?: throw NullPointerException("Groups can not be null")
        allgroupsState.accept(currentGroups.map { group -> group.setIsSelected(group.id == groupId) })
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun getAllTeachersObservable(): Observable<List<TeacherShortModel>> = allTeachersState

    override fun getAllStudentsObservable(): Observable<List<StudentShortModel>> = allStudentsState

    override fun getCurrentLessonTeachersObservable(): Observable<List<TeacherShortModel>> =
        teachersState

    override fun getCurrentLessonStudentsObservable(): Observable<List<StudentShortModel>> =
        studentsState

    override fun getCurrentLessonGroupObservable(): Observable<List<GroupFullModel>> =
        allgroupsState

    override fun saveLesson(): Completable {
        val currentLesson: LessonFullModel =
            lesson ?: throw NullPointerException("lesson can not be null")
        val name: String = name
        val groupId: Long = allgroupsState.value?.first { item -> item.isSelected }?.id
            ?: throw NullPointerException("Group can not be null")
        val currentTeachers = teachersState.value?.filter { it.isSelected }?.map { it.id }
            ?: throw NullPointerException("groups cannot be null")
        val currentStudents = studentsState.value?.filter { it.isSelected }?.map { it.id }
            ?: throw NullPointerException("students cannot be null")

        val newLesson = currentLesson.copy(
            name = name,
            groupId = groupId,
            teachers = currentTeachers,
            students = currentStudents
        )

        return if (newLesson.id == DEFAULT_ID) {
            lessonsRepository.createLesson(newLesson)
        } else {
            lessonsRepository.updateLesson(newLesson)
        }
            .andThen(lessonsRepository.refreshLessons())
    }

    override fun deleteLesson(): Completable {
        val currentLesson: LessonFullModel =
            lesson ?: throw NullPointerException("lesson can not be null")
        return lessonsRepository.deleteLesson(currentLesson)
    }

    override fun clearAll() {
        lesson = null
        name = ""
        teachersState.accept(listOf())
        studentsState.accept(listOf())
        allTeachersState.accept(listOf())
        allStudentsState.accept(listOf())
        allgroupsState.accept(listOf())
    }
}