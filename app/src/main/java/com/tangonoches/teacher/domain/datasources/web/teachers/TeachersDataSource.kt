package com.tangonoches.teacher.domain.datasources.web.teachers

import com.tangonoches.teacher.data.models.TeacherShortModel
import com.tangonoches.teacher.data.responses.teachers.toModel
import com.tangonoches.teacher.domain.datasources.web.login.subToThreads
import com.tangonoches.teacher.domain.services.TeachersService
import io.reactivex.Single

class TeachersDataSource(
    private val teachersService: TeachersService
) : ITeachersDataSource {
    override fun getAllTeachers(): Single<List<TeacherShortModel>> =
        teachersService.getAllTeachers()
            .subToThreads()
            .map { list -> list.map { dto -> dto.toModel() } }
}