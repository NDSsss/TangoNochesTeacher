package com.tangonoches.teacher.domain.repositories.students

import com.tangonoches.teacher.data.models.StudentShortModel
import io.reactivex.Single

interface IStudentsRepository {
    fun getAllStudents():Single<List<StudentShortModel>>
}