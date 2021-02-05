package com.tangonoches.teacher.domain.datasources.web.teachers

import com.tangonoches.teacher.data.models.TeacherShortModel
import io.reactivex.Single

interface ITeachersDataSource {
    fun getAllTeachers():Single<List<TeacherShortModel>>
}