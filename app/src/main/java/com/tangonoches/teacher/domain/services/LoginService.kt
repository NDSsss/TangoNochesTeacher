package com.tangonoches.teacher.domain.services

import com.tangonoches.teacher.data.responses.LoginResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("teacher/getToken")
    @FormUrlEncoded
    fun login(@Field("email") email: String, @Field("password") password: String): Single<LoginResponse>
}