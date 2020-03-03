package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.teachers.TeacherShortDto
import io.reactivex.Single
import retrofit2.http.GET

interface TeachersService {
    @GET("teacher/allTeachers")
    fun getAllTeachers(): Single<List<TeacherShortDto>>
}