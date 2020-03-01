package com.tangonoches.teacher.presentation.login

import com.jakewharton.rxrelay2.BehaviorRelay
import com.tangonoches.teacher.domain.repositories.login.ILoginRepository
import com.tangonoches.teacher.presentation.base.BaseVm
import javax.inject.Inject

class LoginVm @Inject constructor(
    val loginRepository: ILoginRepository
) : BaseVm() {
    val loginEvent = BehaviorRelay.create<Pair<String, String>>()

    val loginSuccessState = BehaviorRelay.create<Unit>()

    override fun createBinds() {
        super.createBinds()
        binds.add(
            loginEvent.subscribe { event ->
                binds.add(
                    loginRepository.login(
                        event.first, event.second
                    ).subscribe({
                        loginSuccessState.accept(Unit)
                    }, {
                        errorRelay.accept(it.localizedMessage?:"Unknown error")
                    })
                )
            }
        )
    }
}