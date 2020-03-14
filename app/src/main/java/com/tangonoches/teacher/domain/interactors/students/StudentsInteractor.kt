package com.tangonoches.teacher.domain.interactors.students

import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import com.tangonoches.teacher.domain.repositories.students.IStudentsRepository
import io.reactivex.Completable
import io.reactivex.Single

class StudentsInteractor(
    private val studentsRepository: IStudentsRepository
) : IStudentsInteractor {
    override fun getAllStudents(): Single<List<StudentShortModel>> =
        studentsRepository.getAllStudents()

    override fun getStudentById(id: Long): Single<StudentFullModel> =
        studentsRepository.getStudentById(id)

    override fun saveStudent(student: StudentFullModel): Completable =
        if (student.id == DEFAULT_ID) {
            studentsRepository.saveStudent(student)
        } else {
            studentsRepository.updateStudent(student)
        }
}