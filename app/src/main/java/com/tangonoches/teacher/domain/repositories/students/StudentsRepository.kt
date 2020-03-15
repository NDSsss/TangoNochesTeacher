package com.tangonoches.teacher.domain.repositories.students

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.datasources.web.students.IStudentsDataSource
import io.reactivex.Completable
import io.reactivex.Observable
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

    override fun getAllStudentsObservable(): Observable<List<StudentShortModel>> = students

    override fun getStudentById(id: Long): Single<StudentFullModel> =
        studentsDataSource.getStudentById(id)

    override fun updateStudent(student: StudentFullModel): Completable =
        studentsDataSource.updateStudent(student)

    override fun saveStudent(student: StudentFullModel): Completable =
        studentsDataSource.saveStudent(student)

    override fun deleteStudent(student: StudentFullModel): Completable =
        studentsDataSource.deleteStudent(student)
            .andThen(refreshStudents())

    override fun refreshStudents(): Completable =
        Completable.fromSingle(
            studentsDataSource.getAllStudents()
                .doOnSuccess { list -> students.accept(list) }
        )
}