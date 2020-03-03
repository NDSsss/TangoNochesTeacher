package com.tangonoches.teacher.domain.repositories.teachers

import com.tangonoches.teacher.data.models.TeacherShortModel
import io.reactivex.Single

interface ITeachersRepository {
    fun getAllTeachers():Single<List<TeacherShortModel>>
}