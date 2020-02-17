package com.tangonoches.teacher.domain.repositories

import io.reactivex.Single

interface ILoginRepository {
    fun login(email: String, password: String): Single<String>
}