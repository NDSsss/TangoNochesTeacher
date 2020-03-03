package com.tangonoches.teacher.domain.datasources.web.students

import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.responses.students.toModel
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.services.StudentsService
import io.reactivex.Single

class StudentsDataSource(
    private val studentsService: StudentsService
) : IStudentsDataSource {
    override fun getAllStudents(): Single<List<StudentShortModel>> =
        studentsService.getStdents(0, 400)
            .subToThreads()
            .map { response ->
                response.data.map { dto ->
                    dto.toModel()
                }
            }
}