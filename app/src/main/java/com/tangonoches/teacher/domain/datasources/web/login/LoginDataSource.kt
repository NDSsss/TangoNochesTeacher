package com.tangonoches.teacher.domain.datasources.web.login

import com.tangonoches.teacher.domain.services.LoginService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginDataSource(
    private val loginService: LoginService
) : ILoginDataSource {
    override fun login(email: String, password: String): Single<String> =
        loginService.login(email, password)
            .map { response -> response.api_token }
            .subToThreads()
}

fun <T : Any> Single<T>.subToThreads(): Single<T> = this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())