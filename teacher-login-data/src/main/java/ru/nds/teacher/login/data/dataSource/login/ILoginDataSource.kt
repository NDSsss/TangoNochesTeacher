package ru.nds.teacher.login.data.dataSource.login

import io.reactivex.Single
import ru.nds.teacher.login.data.dto.LoginDto

interface ILoginDataSource {
    fun login(email: String, password: String): Single<LoginDto>
}