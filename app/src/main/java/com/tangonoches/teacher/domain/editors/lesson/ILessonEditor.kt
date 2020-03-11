package com.tangonoches.teacher.domain.editors.lesson

import com.tangonoches.teacher.data.models.GroupFullModel
import com.tangonoches.teacher.data.models.LessonFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.models.TeacherShortModel
import io.reactivex.Observable

interface ILessonEditor {
    fun setLessonToEdit(lesson: LessonFullModel)

    fun addTeacher(teacher: TeacherShortModel)
    fun removeTeacher(teacher: TeacherShortModel)

    fun addStudent(student: StudentShortModel)
    fun removeStudent(student: StudentShortModel)

    fun setGroup(group: GroupFullModel)

    fun setName(name: String)

    fun getAllTeachersObservable(): Observable<List<TeacherShortModel>>
    fun getAllStudentsObservable(): Observable<List<StudentShortModel>>

    fun getCurrentLessonTeachersObservable(): Observable<List<TeacherShortModel>>
    fun getCurrentLessonStudentsObservable(): Observable<List<StudentShortModel>>
}