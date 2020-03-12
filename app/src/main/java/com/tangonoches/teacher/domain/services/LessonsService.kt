package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.lessons.AllLessonsResponse
import com.tangonoches.teacher.data.responses.lessons.LessonByIdResponse
import com.tangonoches.teacher.data.responses.lessons.LessonCreateDto
import com.tangonoches.teacher.data.responses.lessons.LessonUpdateDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LessonsService {

    @POST("teacher/allLessons")
    @FormUrlEncoded
    fun getAllLessons(@Field("page") page: Int, @Field("count_on_page") countOnPage: Int): Single<AllLessonsResponse>

    @POST("teacher/lessonById")
    @FormUrlEncoded
    fun getLessonById(@Field("id") id: Long): Single<LessonByIdResponse>

    @POST("teacher/updateLesson")
    fun updateLesson(@Body lesson: LessonUpdateDto): Completable

    @POST("teacher/registerLesson")
    fun createLesson(@Body lesson: LessonCreateDto): Completable

}