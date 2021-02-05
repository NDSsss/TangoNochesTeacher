package ru.nds.teacher.login.data.dataSource.login

import io.reactivex.Single
import ru.nds.common.rx.ISchedulers
import ru.nds.common.rx.utils.subToThreads
import ru.nds.teacher.login.data.api.LoginApiService
import ru.nds.teacher.login.data.dto.LoginDto

class LoginDataSource(
    private val schedulers: ISchedulers,
    private val loginApiService: LoginApiService
) : ILoginDataSource {
    override fun login(email: String, password: String): Single<LoginDto> =
        loginApiService.login(email, password)
            .subToThreads(schedulers)
}