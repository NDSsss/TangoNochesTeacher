package com.tangonoches.teacher.domain.services

import com.google.gson.JsonElement
import com.tangonoches.teacher.data.responses.students.AllStudentsResponse
import com.tangonoches.teacher.data.responses.students.StudentByIdResponse
import com.tangonoches.teacher.data.responses.students.StudentFullDtoToSave
import com.tangonoches.teacher.data.responses.students.StudentFullDtoToUpdate
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.*
import java.lang.reflect.WildcardType

interface StudentsService {
    @POST("teacher/allStudents")
    @FormUrlEncoded
    fun getStudents(@Field("page") page: Int, @Field("count_on_page") countOnPage: Int): Single<AllStudentsResponse>

    @POST("teacher/studentById")
    @FormUrlEncoded
    fun getStudentById(@Field("id") id: Long): Single<StudentByIdResponse>

    @POST("teacher/updateStudent")
    @Headers("Content-Type: application/json")
    fun updateStudent(@Body studentUpdateMap: RequestBody):Completable

    @POST("teacher/registerStudent")
    @Headers("Content-Type: application/json")
    fun saveStudent(@Body studentSaveMap: RequestBody):Completable
}