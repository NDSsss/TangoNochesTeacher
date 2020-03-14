package com.tangonoches.teacher.domain.datasources.web.students

import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import io.reactivex.Completable
import io.reactivex.Single

interface IStudentsDataSource {
    fun getAllStudents():Single<List<StudentShortModel>>
    fun getStudentById(id:Long):Single<StudentFullModel>
    fun updateStudent(student:StudentFullModel):Completable
    fun saveStudent(student:StudentFullModel):Completable
}