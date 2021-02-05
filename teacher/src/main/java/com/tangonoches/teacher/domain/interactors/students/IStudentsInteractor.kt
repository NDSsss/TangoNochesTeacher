package com.tangonoches.teacher.domain.interactors.students

import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IStudentsInteractor {
    fun getAllStudents(): Single<List<StudentShortModel>>
    fun getAllStudentsObservable(): Observable<List<StudentShortModel>>
    fun refreshStudents():Completable
    fun getStudentById(id:Long):Single<StudentFullModel>
    fun saveStudent(student: StudentFullModel):Completable
    fun deleteStudent(student: StudentFullModel):Completable
}