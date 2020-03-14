package com.tangonoches.teacher.domain.datasources.web.students

import com.google.gson.Gson
import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.responses.students.toModel
import com.tangonoches.teacher.data.responses.students.toSaveJsonObject
import com.tangonoches.teacher.data.responses.students.toSaveMap
import com.tangonoches.teacher.data.responses.students.toUpdateMap
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.services.StudentsService
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class StudentsDataSource(
    private val studentsService: StudentsService,
    private val gson: Gson
) : IStudentsDataSource {
    override fun getAllStudents(): Single<List<StudentShortModel>> =
        studentsService.getStudents(0, 400)
            .subToThreads()
            .map { response ->
                response.data.map { dto ->
                    dto.toModel()
                }
            }

    override fun getStudentById(id: Long): Single<StudentFullModel> =
        studentsService.getStudentById(id)
            .subToThreads()
            .map { response ->
                response.data.toModel()
            }

    override fun updateStudent(student: StudentFullModel): Completable =
        studentsService.updateStudent(gson.toJson(student.toUpdateMap()).toRequestBody("application/json".toMediaTypeOrNull()))
            .subToThreads()

    override fun saveStudent(student: StudentFullModel): Completable =
        studentsService.saveStudent(gson.toJson(student.toSaveMap()).toRequestBody("application/json".toMediaTypeOrNull()))
            .subToThreads()
}