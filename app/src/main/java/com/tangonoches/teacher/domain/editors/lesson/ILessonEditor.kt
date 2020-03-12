package com.tangonoches.teacher.domain.editors.lesson

import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.models.TeacherShortModel
import io.reactivex.Completable
import io.reactivex.Observable

interface ILessonEditor {
    fun setLessonToEdit(lesson: LessonFullModel)

    fun addTeacher(teacherId: Long)
    fun removeTeacher(teacherId: Long)

    fun addStudent(studentId: Long)
    fun removeStudent(studentId: Long)

    fun groupSelected(groupId: Long)

    fun setName(name: String)

    fun getAllTeachersObservable(): Observable<List<TeacherShortModel>>
    fun getAllStudentsObservable(): Observable<List<StudentShortModel>>

    fun getCurrentLessonTeachersObservable(): Observable<List<TeacherShortModel>>
    fun getCurrentLessonStudentsObservable(): Observable<List<StudentShortModel>>
    fun getCurrentLessonGroupObservable(): Observable<List<GroupFullModel>>

    fun saveLesson(): Completable
    fun clearAll()
}