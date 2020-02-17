package com.tangonoches.teacher.domain.datasources.web.login

import com.tangonoches.teacher.data.responses.LoginResponse
import io.reactivex.Single

interface ILoginDataSource {
    fun login(email: String, password: String): Single<LoginResponse>
}