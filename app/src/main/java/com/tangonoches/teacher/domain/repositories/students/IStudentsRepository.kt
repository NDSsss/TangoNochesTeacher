package com.tangonoches.teacher.domain.repositories.students

import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IStudentsRepository {
    fun getAllStudents():Single<List<StudentShortModel>>
    fun getAllStudentsObservable():Observable<List<StudentShortModel>>
    fun getStudentById(id:Long):Single<StudentFullModel>
    fun updateStudent(student:StudentFullModel): Completable
    fun saveStudent(student:StudentFullModel):Completable
    fun deleteStudent(student:StudentFullModel):Completable

    fun refreshStudents():Completable
}