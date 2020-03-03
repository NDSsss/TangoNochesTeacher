package com.tangonoches.teacher.domain.datasources.web.students

import com.tangonoches.teacher.data.models.StudentShortModel
import io.reactivex.Single

interface IStudentsDataSource {
    fun getAllStudents():Single<List<StudentShortModel>>
}