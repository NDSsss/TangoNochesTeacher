package com.tangonoches.teacher.domain.repositories.students

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.datasources.web.students.IStudentsDataSource
import io.reactivex.Single

class StudentsRepository(
    private val studentsDataSource: IStudentsDataSource
) : IStudentsRepository {

    private val students = BehaviorRelay.create<List<StudentShortModel>>()

    override fun getAllStudents(): Single<List<StudentShortModel>> =
        if (students.hasValue().not()) {
            studentsDataSource.getAllStudents()
                .doOnSuccess { list -> students.accept(list) }
        } else {
            Single.just(students.value)
        }
}