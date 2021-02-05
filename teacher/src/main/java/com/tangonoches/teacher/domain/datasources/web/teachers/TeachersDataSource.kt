package com.tangonoches.teacher.domain.datasources.web.teachers

import com.tangonoches.teacher.data.models.TeacherShortModel
import com.tangonoches.teacher.data.responses.teachers.toModel
import com.tangonoches.teacher.domain.services.TeachersService
import io.reactivex.Single
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.utils.subToThreads

class TeachersDataSource(
    private val schedulers: ISchedulers,
    private val teachersService: TeachersService
) : ITeachersDataSource {
    override fun getAllTeachers(): Single<List<TeacherShortModel>> =
        teachersService.getAllTeachers()
            .subToThreads(schedulers)
            .map { list -> list.map { dto -> dto.toModel() } }
}