package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.lessons.AllLessonsResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LessonsService {

    @POST("teacher/allLessons")
    @FormUrlEncoded
    fun getAllLessons(@Field("page") page: Int, @Field("count_on_page") countOnPage: Int): Single<AllLessonsResponse>
}