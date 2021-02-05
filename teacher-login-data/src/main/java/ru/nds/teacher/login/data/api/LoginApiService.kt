package ru.nds.teacher.login.data.api

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.nds.teacher.login.data.dto.LoginDto

interface LoginApiService {
    @POST("teacher/getToken")
    @FormUrlEncoded
    fun login(@Field("email") email: String, @Field("password") password: String): Single<LoginDto>
}