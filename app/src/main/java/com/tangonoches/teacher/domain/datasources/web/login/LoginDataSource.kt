package com.tangonoches.teacher.domain.datasources.web.login

import com.tangonoches.teacher.data.responses.LoginResponse
import com.tangonoches.teacher.domain.services.LoginService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginDataSource(val loginService: LoginService) : ILoginDataSource {
    override fun login(email: String, password: String): Single<LoginResponse> =
        loginService.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}