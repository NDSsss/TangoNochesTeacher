package com.tangonoches.teacher.domain.datasources.web.students

import com.google.gson.Gson
import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.data.models.StudentShortModel
import com.tangonoches.teacher.data.responses.students.toModel
import com.tangonoches.teacher.data.responses.students.toSaveMap
import com.tangonoches.teacher.data.responses.students.toUpdateMap
import com.tangonoches.teacher.domain.services.StudentsService
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.utils.subToThreads

class StudentsDataSource(
    private val schedulers: ISchedulers,
    private val studentsService: StudentsService,
    private val gson: Gson
) : IStudentsDataSource {
    override fun getAllStudents(): Single<List<StudentShortModel>> =
        studentsService.getStudents(0, 400)
            .subToThreads(schedulers)
            .map { response ->
                response.data.map { dto ->
                    dto.toModel()
                }
            }

    override fun getStudentById(id: Long): Single<StudentFullModel> =
        studentsService.getStudentById(id)
            .subToThreads(schedulers)
            .map { response ->
                response.data.toModel()
            }

    override fun updateStudent(student: StudentFullModel): Completable =
        studentsService.updateStudent(
            gson.toJson(student.toUpdateMap()).toRequestBody("application/json".toMediaTypeOrNull())
        )
            .subToThreads(schedulers)

    override fun saveStudent(student: StudentFullModel): Completable =
        studentsService.saveStudent(
            gson.toJson(student.toSaveMap()).toRequestBody("application/json".toMediaTypeOrNull())
        )
            .subToThreads(schedulers)

    override fun deleteStudent(student: StudentFullModel): Completable =
        studentsService.deleteStudent(student.id)
            .subToThreads(schedulers)
}