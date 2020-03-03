package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.students.AllStudentsResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface StudentsService {
    @POST("teacher/allStudents")
    @FormUrlEncoded
    fun getStdents(@Field("page") page: Int, @Field("count_on_page") countOnPage: Int): Single<AllStudentsResponse>
}